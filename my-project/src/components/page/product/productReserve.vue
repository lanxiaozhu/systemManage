<!-- 产品预定-->
<template>
<div>

<el-input v-model="input"    size="small" prefix-icon="el-icon-search"	 placeholder="请输入内容" style="width:150px;float:left"></el-input>
  <el-button type="primary"  style="width:70px;float:left" plain size="small">查询</el-button>
  <el-button type="danger" style="float:left;margin-left:0px" icon="el-icon-delete"
  size="small"
   plain v-on:click="rmAll">全选删除</el-button>

  <el-table
    :data="tableData"
    style="width: 100%"
    @selection-change="handleSelectionChange">

  <el-table-column
      type="selection"
      width="55">
    </el-table-column>

    <el-table-column
        label="编号"
        width="180">
        <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
    </el-table-column>

    <el-table-column
      label="预定日期"
      width="180">
      <template slot-scope="scope">
        <i class="el-icon-time"></i>
        <span style="margin-left: 10px">{{ scope.row.date }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="预定人姓名"
      width="180">
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>姓名: {{ scope.row.name }}</p>
          <p>住址: {{ scope.row.address }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.name }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
            <div class="block" style="float:right">
                <el-pagination
                    @current-change="handleCurrentChange"
                    layout="prev, pager, next"
                    :total="50">
                </el-pagination>
            </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      input: null,
      arr: [],
      tableData: [
        {
          id: 1,
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          id: 2,
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        },
        {
          id: 3,
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        },
        {
          id: 4,
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }
      ]
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
      alert(currentPage)
    },
    doQuery () {
      /* axios
        .get("http://localhost:8080/login/getListNo")
        .then(response => {
          console.log(response);
          if (response.data["code"] == "200") {
            console.log("登陆成功");
            this.$router.push("main");
          } else {
            alert(response.data["result"]);
          }
        })
        .catch(error => {
          console.log(error);
          alert("网络错误，不能访问");
          //  this.$router.push('main')
        }); */
    }
  },
  mounted () {
    this.doQuery()
  }
}
</script>
