<template>
  <div class="menu">
    <el-table :data="tableData" style="width: 100%" height="650">
      <!--	<el-table-column prop="id" label="ID" ></el-table-column> -->
      <TableTreeColumn prop="menuName" treeKey="id" label="菜单名称"></TableTreeColumn>
      <el-table-column label="图标">
        <span slot-scope="scope">
               <i :class="scope.row.menuIcon"></i>
            </span>
      </el-table-column>
      <el-table-column prop="permissionCode" label="权限码"></el-table-column>
      <el-table-column prop="menuUrl" label="菜单URL"></el-table-column>
      <el-table-column prop="menuLever" label="层级">
        <span slot-scope="scope">
                <p v-if="scope.row.menuLever == 0">顶级</p>
                <p v-else>{{scope.row.menuLever}}级</p>
    		      </span>
      </el-table-column>
      <el-table-column prop="sort" label="sort排序"></el-table-column>
      <el-table-column label="操作" width="280">
        <template slot-scope="scope">
                <el-button size="mini" v-if="scope.row.menuLever != 0"  @click="handlerUpDownMeun(scope.row)">编辑</el-button>
                <el-button size="mini" type="primary" plain @click="handlerAddDownMeun(scope.row)">添加下级菜单</el-button>
                <el-button size="mini" v-if="scope.row.menuLever != 0" type="danger" plain  @click="handleDelete(scope.$index, scope.row)">删除</el-button>

</template>
		    </el-table-column>
		</el-table>


    <!-- 添加下级菜单 -->
     <el-dialog
    title="添加下级菜单"
    :visible.sync="addDownMeun"
    width="25%">
          <!-- menuType: null,
          permissionCode: null,
          menuUrl: null,
          menuIcon: null,
          sort: null, -->
      <el-form  :model="menu"  label-width="110px" size="mini">
        <el-form-item label="菜单名称:">
          <el-input v-model="menu.menuName"></el-input>
        </el-form-item>
         <el-form-item label="类型:" > 
          <el-select v-model="menu.menuType" placeholder="请选择菜单类型">
            <el-option v-for="item in menuType" :label="item.lable" :key="item.val" :value="item.val"></el-option>
          </el-select>
        </el-form-item> 

        <el-form-item label="权限标识码:">
          <el-input v-model="menu.permissionCode"></el-input>
        </el-form-item>
         <el-form-item label="链接:">
          <el-input v-model="menu.menuUrl"></el-input>
        </el-form-item>
         <el-form-item label="图标:">
          <el-input v-model="menu.menuIcon"></el-input>
        </el-form-item>
           <el-form-item label="排列顺序:">
          <el-input v-model="menu.sort"></el-input>
        </el-form-item>
      </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="addDownMeun = false">取 消</el-button>
      <el-button @click="saveMenu" type="primary" >提 交</el-button>
    </span>
  </el-dialog>
  
	</div>
</template>

<style scoped>
  @import "../../css/all.css";
</style>

<script>
  import TableTreeColumn from '@/components/table-tree-column.vue'
  import {
    getInfo,
    getParam,
    postParam,
    delParam,
    putParam
  } from '@/components/axios/api.js'
  //消息提示
  import {
    success,
    warning
  } from '../../js/systemManage/alert.js'
  export default {
    components: {
      TableTreeColumn
    },
    data() {
      return {
        tableData: null, // 列表显示数据
        addDownMeun: false, //添加下级菜单的弹出框
        parentId: null, //添加下级菜单 提交信息时，需要知道父级Id
        isNewAdd: false, //添加下级菜单 或者 修改
        menuType: [{
            val: 1,
            lable: '菜单'
          },
          {
            val: 2,
            lable: '目录'
          },
          {
            val: 3,
            lable: '按钮'
          },
        ],
        menu: {
          id: null,
          menuName: null,
          menuType: null,
          permissionCode: null,
          menuUrl: null,
          menuIcon: null,
          sort: null,
          menuLever: null
        },
      }
    },
    created: function() {
      this.doQuery()
    },
    methods: {
      doQuery() {
        const param = {
          page: 1,
          size: 10
        }
        getParam(this.$route.path, param).then(response => {
          if (response.data.code == 200) {
            this.tableData = response.data.data
            // this.total = response.data.page.total;
          }
        }).catch(error => {
          console.log(error)
          alert('网络错误，不能访问')
          this.$router.push('/')
        })
      },
      /**
       * 添加下级菜单
       */
      saveMenu() {
        let _th = this;
        this.isNewAdd ? this.insert(_th) : this.update(_th);
      },
      insert(_th) {
        const param = {
          parentId: this.parentId,
          menu: this.menu
        }
        postParam(this.$route.path, param)
          .then(response => {
            if (response.data.code == 200) {
              success(response.data.msg, _th)
              this.doQuery()
              this.addDownMeun = false;
            }
          })
      },
      update(_th) {
        const param = {
          menu: this.menu
        }
        putParam(this.$route.path, param)
          .then(response => {
            if (response.data.code == 200) {
              success(response.data.msg, _th)
              this.doQuery()
              this.addDownMeun = false;
            }
          })
      },
      handleDelete(index, row) {
        console.log(row)
      },
      handlerAddDownMeun(row) {
        this.isNewAdd = true;
        this.parentId = row.id;
        this.menu.menuLever = (row.menuLever + 1);
        this.addDownMeun = true;
      },
      /**
       * 点击编辑按钮
       */
      handlerUpDownMeun(row) {
        this.isNewAdd = false;
        this.addDownMeun = true;
        this.menu.id = row.id;
        this.menu.menuName = row.menuName;
        this.menu.menuType = row.menuType;
        this.menu.permissionCode = row.permissionCode;
        this.menu.menuUrl = row.menuUrl;
        this.menu.menuIcon = row.menuIcon;
        this.menu.sort = row.sort;
        this.menu.menuLever = row.menuLever;
      }
    }
  }
</script>
