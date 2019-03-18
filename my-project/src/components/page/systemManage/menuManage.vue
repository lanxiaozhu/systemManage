<template>
	<div class="menu">
		<el-table :data="tableData" style="width: 100%" height="660">
		<!--	<el-table-column prop="id" label="ID" ></el-table-column> -->
			<TableTreeColumn prop="menuName" treeKey="id"  label="菜单名称"></TableTreeColumn>

			<el-table-column label="图标">
        <span slot-scope="scope">
           <i :class="scope.row.menuIcon"></i>
        </span>
      </el-table-column>

			<el-table-column prop="menuUrl" label="菜单URL"></el-table-column>
      <el-table-column prop="menuLever" label="层级">
         <span slot-scope="scope">
            <p v-if="scope.row.menuLever == 0">顶级</p>
            <p v-else>{{scope.row.menuLever}}级</p>
		      </span>
      </el-table-column>

			<el-table-column label="操作" width="280">
		      <template slot-scope="scope">
            <el-button size="mini" v-if="scope.row.menuLever != 0">编辑</el-button>
            <el-button size="mini" v-if="scope.row.menuLever != 0"  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <el-button size="mini">添加下级菜单</el-button>

		      </template>
		    </el-table-column>
		</el-table>
	</div>
</template>

<script>
import TableTreeColumn from '@/components/table-tree-column.vue'
import {getInfo,getParam,postParam,delParam,putParam} from '@/components/axios/api.js'


export default {
  components: {
    TableTreeColumn
  },
  data () {
    return {
      	tableData: null// 列表显示数据
    }
  },
  	created: function () {
    this.doQuery()
  },
  methods: {
    doQuery () {

      const param =  {
            page: 1,
            size: 10
            }
     getParam(this.$route.path,param).then(response => {
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
    handleDelete (index, row) {
      console.log(row)
    }

  }
}
</script>
