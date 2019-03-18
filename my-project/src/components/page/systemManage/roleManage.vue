
<!-- 角色管理-->
<template>
  <div style="height:600px;color:#fff;">

  <el-input v-model="inputParam"  size="small" prefix-icon="el-icon-search"	 placeholder="请输入内容" style="width:150px;float:left"></el-input>
    <el-button type="primary"  style="width:70px;float:left" plain size="small">查询</el-button>
    <el-button type="success"  style="width:70px;float:left" plain size="small" @click="dialogVisible = true">新增</el-button>
    <el-button type="danger" style="width:90px;float:left" icon="el-icon-delete" size="small"
    plain v-on:click="rmAll">全选删除</el-button>

  <el-table
    :data="tableData"
    style="width: 100%"
    height="300"
    @row-click="clickRow"
    :row-class-name="tableRowClassName"
    :row-style="selectedHighlight"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>

 <el-table-column
        prop="id"
        label="角色编号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="roleName"
        label="角色名称"
        width="180">
    </el-table-column>
  <el-table-column
        prop="description"
        label="角色描述"
        width="180">
    </el-table-column>

       <el-table-column
        label="添加时间"
        width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.gtmCreated | dateformat('YYYY-MM-DD HH:mm:ss') }}</span>
        </template>

    </el-table-column>

    <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button  size="mini"  @click="handleEdit(scope.$index, scope.row)">   {{scope.row.state == 10 ?  '禁用':'启用'}} </el-button>
            <el-button  size="mini"  type="danger"  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
    </el-table-column>
  </el-table>
  <div class="block" style="float:right">
          <el-pagination
              @current-change="handleCurrentChange"
              layout="prev, pager, next"
              :total="total">
          </el-pagination>
      </div>

    
<!--角色菜单，表格树内容栏-->
	<div class="menu-container" :v-if="true">
		<div class="menu-header">
			<span><B>角色菜单授权</B></span>
		</div>
    <!--show-checkbox  节点是否可被选择	 check-strictly 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法，默认为 false-->
		<el-tree   ref="tree" :data="menu" size="mini" show-checkbox node-key="id" :props="defaultProps"  :default-checked-keys="roleMenuIds"
			style="width: 100%;pading-top:20px;" :render-content="renderContent"
			:check-strictly="true"  @check-change="handleCheckChange">
		</el-tree>

		<div style="float:left;padding-left:24px;padding-top:12px;padding-bottom:4px;">
			<el-checkbox v-model="checkAll" @change="handleCheckAll"><b>全选</b></el-checkbox>
		</div>
		<div style="float:right;padding-right:15px;padding-top:4px;padding-bottom:4px;">
			<el-button  type="primary" @click="resetSelection" size="mini" :disabled="initTreeSub">重置</el-button>
			<el-button  type="primary" @click="submitAuthForm" size="mini" :disabled="initTreeSub">提交</el-button>
		</div>
  </div>

  <el-dialog
    title="新增角色"
    :visible.sync="dialogVisible"
    width="25%">

    <el-form ref="form" :model="role" label-width="100px" size="mini">
      <el-form-item label="名称">
        <el-input v-model="role.roleName" class="wS"></el-input>
      </el-form-item>

      <el-form-item label="描述">
        <el-input v-model="role.description" class="wS"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveRole">提 交</el-button>
    </span>
  </el-dialog>

    

  </div>
</template>

<style scoped>
@import "../../css/all.css";
/* 下表树样式 */
.menu-container {
	margin-top: 10px;
}
.menu-header {
	padding-left: 8px;
	padding-bottom: 5px;
	text-align: left;
	font-size: 16px;
	color: rgb(20, 89, 121);
}
/* 选中当前行的背景颜色 */
.el-main{
  background-color: #ffffff
}
</style>

<script>
import {getInfo,getParam,postParam,delParam,putParam} from '@/components/axios/api.js'
import { success,warning } from '../../js/systemManage/alert.js'

export default {
  data () {
    return {
      getIndex: null,
      dialogVisible: false, // 控制添加按钮弹出框
      roleAllocation: false,
      addUserState: true,
      tempName: '',
      role: {
        roleName: null,
        description: null
      },
      inputParam: null,
      arr: [],
      menu: [],//下表树 显示全部的菜单数据
      roleMenuIds: [],//角色的所有菜单Id
      //roleMenuIdResult: [],//角色选中的所有菜单Id
      checkAll: false,//全选
      initTreeSub: true,//表格树提交按钮 初始化时禁用的
      selectedRoleId: null,//点击后的角色Id
      tableData: [],
      page: 1,
      size: 10,
      total: 0,
      //tree 结构
      defaultProps: {
        children: 'menuList',
        label: 'menuName'
      },
    }
  },
  methods: {
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
      this.open4(row)
    },
    handleSelectionChange (val) {
      console.log(val)
      this.arr = []
      for (var i = 0; i < val.length; i++) {
        this.arr.push(val[i].id)
      }
    },
    rmAll () {
      console.log(this.arr)
    },
    open4 (row) {
      this.$message.error('小心点要删除了：' + row.id)
    },
    handleCurrentChange: function (currentPage) {
      this.page = currentPage
      this.doQuery()
    },
    doQuery () {
      const params = {
            page: this.page,
            size: this.size
      }
     getParam(this.$route.path,params)
        .then(response => {
          if (response.data.code == 200) {
            this.tableData = response.data.data
            this.total = response.data.page.total
          }
        })
    },
    // 添加用户
    saveRole () {
      let _th = this
     postParam(this.$route.path, this.role)
        .then(response => {
          if (response.data.code == 200) {
            success(response.data.msg,_th)
          } else {
            warning(response.data.msg,_th)
          }
          this.dialogVisible = false
          this.doQuery()
        })
    },
    /**
     * 查询全部菜单
     */
    queryMenu(){
        getInfo('menu/qeuryAll').then(res => {
          if (res.data.code == '200') {
            this.menu = res.data.data
          }
        });
    },
    clickRow(row, column, event){
      this.getIndex=row.index//back
      this.initTreeSub = false;
      this.selectedRoleId = row.id;
      const param =  {
        roleId: row.id,
      } 
      this.resetSelection();
      let menuIds = [];
      this.roleMenuIds.length = 0;
      getParam('/roleMenuRelation',param).then(res => {
          if (res.data.code == '200') {
            for(let item of res.data.data){
                menuIds.push(item.id);
            }
          }
          this.roleMenuIds = menuIds;
      })
    },
    selectedHighlight({row, rowIndex}) {//back
      if ((this.getIndex) === rowIndex ) {
        return { "background-color": '#c0c4cc66' };
      }},
      tableRowClassName ({row, rowIndex}) {//back
      //把每一行的索引放进row
      row.index = rowIndex;
      },
  
    handleCheckAll(){
      if(this.checkAll) {
				let allMenus = []
				this.checkAllMenu(this.menu, allMenus)
        this.$refs.tree.setCheckedNodes(allMenus)
			} else {
				this.$refs.tree.setCheckedNodes([])
			}
    }, 
    // 递归全选
		checkAllMenu(menuData, allMenus) {
			menuData.forEach(menu => {
				allMenus.push(menu)
				if(menu.menuList) {
					this.checkAllMenu(menu.menuList, allMenus)
				}
			});
		},
    resetSelection(){
      this.checkAll = false
       this.$refs.tree.setCheckedKeys([]);
    },
    //提交
    submitAuthForm(){
      let _th = this;
     let checkedNodes = this.$refs.tree.getCheckedNodes(false, true)
     let menus = [];
     for(let item of checkedNodes){
       menus.push(item.id)
     }
      const params = {
        roleId: this.selectedRoleId,
        munes: menus
      }
      postParam('/roleMenuRelation',params).then(response => {
       if (response.data.code == 200) {
            success(response.data.msg,_th)
          } else {
            warning(response.data.msg,_th)
          }
      })
    },
    //表格树 选择框 触发
    handleCheckChange(data, checked, indeterminate) {
        if(checked) {
          // 节点选中时同步选中父节点
          let parentId = data.parentId
          this.$refs.tree.setChecked(parentId, true, false)
			  } else {
          // 节点取消选中时同步取消选中子节点
          if(data.menuList != null) {
            data.menuList.forEach(element => {
              this.$refs.tree.setChecked(element.id, false, false)
            });
				  }
			  }
    },
    renderContent(h, { node, data, store }) {
			return (
        <div class="column-container">
          <span style="text-algin:center;margin-right:80px;">{data.menuName}</span>
          <span style="text-algin:center;margin-right:80px;">
            <el-tag type={data.menuLever === 1 ?'success':'danger'} size="small">
              {data.menuLever === 1 ?'目录':data.menuLever === 2 ?'菜单':'按钮'}
            </el-tag>
          </span>
          <span style="text-algin:center;margin-right:80px;"> <i class={data.menuIcon}></i></span>
          <span style="text-algin:center;margin-right:80px;">{data.menuLever  == 1 ? '次顶级菜单' : '功能菜单'}</span>
          <span style="text-algin:center;margin-right:80px;">{data.menuUrl?data.menuUrl:'\t'}</span>
        </div>
      );
    },
  },
  mounted () {
    this.doQuery();
    this.queryMenu();
  }
}
</script>
