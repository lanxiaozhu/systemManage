
<template>
  <div clss="test">
    <!-- prefix-icon 输入框前置 图标-->
    <el-input v-model="userName" size="small" prefix-icon="el-icon-search" placeholder="请输入内容" style="width:150px;float:left"></el-input>
    <el-button type="primary" style="width:70px;float:left" size="small" @click="doQuery">查询</el-button>
    <el-button type="primary" style="width:70px;float:left" plain size="small" @click="doClean">重置</el-button>
    <el-button type="success" style="width:70px;float:left" plain size="small" @click="handleOpenAdd">新增</el-button>
    <el-button type="danger" style="width:90px;float:left" icon="el-icon-delete" size="small" plain v-on:click="rmAll">全选删除</el-button>
    <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="用户编号" width="180">
      </el-table-column>
      <el-table-column prop="realName" label="用户姓名" width="180">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="180">
      </el-table-column>
      <el-table-column label="状态" width="180">
        <template slot-scope="scope">
                                                <span v-if=" scope.row.state == '10'" style="color: #5cadff">启用</span>
                                                <span v-else style="color: #E3CEAB;">禁用</span>
</template>
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
  <!-- <el-button size="mini" @click="handleAllocation(scope.$index, scope.row)">
    分配角色</el-button> -->
  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
  <el-button size="mini" @click="isValid(scope.$index, scope.row)"> {{scope.row.state == 10 ? '禁用':'启用'}} </el-button>
  <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
</template>
    </el-table-column>
  </el-table>

  <!-- 新增用户-->
  <el-dialog
    title="提示"
    :visible.sync="dialogVisible"
    width="30%">
  <el-form ref="form" :model="user" label-width="110px" size="mini">
      <el-form-item label="账号">
        <el-input v-model="user.loginName" class="wS"></el-input>
      </el-form-item>
      <el-form-item label="密码：">
        <el-input type="password" v-model="user.password" class="wS"></el-input>
      </el-form-item>
      <el-form-item label="用户名称">
        <el-input v-model="user.realName" class="wS"></el-input>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="user.phoneNo" class="wS"></el-input>
      </el-form-item>
      <el-form-item label="居住地址">
        <el-input v-model="user.address" class="wS"></el-input>
      </el-form-item>

      <el-form-item label="配置角色">
        <el-select v-model="roleId" multiple placeholder="请选择">
            <el-option
                v-for="item in roleInfo"
                :key="item.id"
                :label="item.roleName"
                :value="item.id">
              </el-option>
          </el-select>
       </el-form-item>

      <el-form-item label="状态">
      <el-switch v-model="addUserState" class="wS"></el-switch>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="subUser">提 交</el-button>
    </span>
  </el-dialog>

<!-- 分配角色 -->
<!-- <el-dialog
  title="分配角色"
  :visible.sync="allocation"
  width="25%">
 <el-table
      ref="multipleTable"
      :data="roleTableData"
      style="width: 100%"
       @selection-change="handleSelectionChangeAllocation">

 <el-table-column
      type="selection"
      width="55">
    </el-table-column>

      <el-table-column
        prop="roleName"
        label="角色名称">
      </el-table-column>

    </el-table>
<span slot="footer" class="dialog-footer">
    <el-button @click="allocation = false">取 消</el-button>
    <el-button type="primary" @click="saveUserRoleRelation" plain>保存分配</el-button>
  </span>
</el-dialog> -->

  <!-- 分页插件-->
    <div class="block" style="float:right">
        <el-pagination
            @current-change="handleCurrentChange"
            layout="prev, pager, next"
            :total="total">
        </el-pagination>
    </div>

  </div>

</template>

<style scoped>
  @import "../../css/all.css";
</style>

<script>
//消息提示
  import { success, warning } from '../../js/systemManage/alert.js'
  // qs 类似于JSON.stringify转换格式的一种方法
  import qs from 'qs'
  import {
    Loading as load
  } from 'element-ui'
  import {getInfo,getParam,postParam,delParam,putParam} from '@/components/axios/api.js'
import { get } from 'https';

  export default {
    data() {
      return {
        isChange: false,//是否是新增 
        dialogVisible: false, // 控制添加按钮弹出框
        //allocation: false, // 分配角色按钮
        addUserState: true,//添加用户弹窗的状态 必须使用 true false
        userId: null, // 被分配的用户ID 临时赋值
        userName: null, // 搜索栏 名称
        user: {
          loginName: null,
          password: null,
          realName: null,
          phoneNo: null,
          address: null,
          state: 10
        },
        roleId: [],//选中的 角色Id 或者分配的角色Id
        roleInfo: [],//角色信息
        arr: [],
        tableData: [],
        roleTableData: [], // 分配角色 角色内容体
        multipleSelection: [],//批量删除 数组
        page: 1,
        size: 10,
        total: 0
      }
    },
    methods: {
    
      // 查询用户数据
      doQuery() {
        //load open
        let loadingInstance = load.service({
          text: '请稍等',
          target: document.querySelector('.el-main') // 设置加载居于块
        })
         const param =  {
              name: this.userName,
              page: this.page,
              size: this.size
            }
          getParam(this.$route.path, param).then(response => {
            if (response.data.code == 200) {
              this.tableData = response.data.data
              this.total = response.data.page.total
            }
            //load close
            loadingInstance.close()
          })
      },
      //获取角色信息
      doGetRoleInfo(){
        getInfo('/roleManageAll').then(response => {
          if (response.data.code == 200) {
            this.roleInfo = response.data.data
          }
        })
      },
      // 添加 或者修改 用户
      subUser() {
        let _th = this
        this.user.state = this.addUserState ? 10 : 20
        let loadingInstance = load.service({
          text: '请稍等',
          target: document.querySelector('.el-main') // 设置加载居于块
        })
        if(this.isChange){
          putParam(this.$route.path, this.user)
            .then(response => {
              this.upUserRoleRelation(this.userId);
          })
        }else{
            postParam(this.$route.path, this.user)
                .then(response => {
                  this.saveUserRoleRelation(response.data.data);
                })
          }
           loadingInstance.close()
      },
       //处理单条删除
      handleDelete(index, row) {
        this.multipleSelection = []
        this.multipleSelection.push(row.id)
        this.rmAll()
      },
      // 处理全部删除
      rmAll() {
        let _th = this
        let loadingInstance = load.service({
          text: '请稍等',
          target: document.querySelector('.el-main') // 设置加载居于块
        })
        if (this.multipleSelection.length <= 0) {
          return warning('请选择至少一条记录', _th)
        }
          const ps=  {
              ids: qs.stringify(this.multipleSelection)
            }
          this.axios
          .delete(this.$route.path, {
            params: {
              ids: this.multipleSelection
            },
            paramsSerializer: params => {
              return qs.stringify(params, {
                indices: false
              })
            }
          })
          .then(response => {
            if (response.data.code == 200) {
              success(response.data.msg, _th)
              this.doQuery()
              loadingInstance.close()
            }
          })
      },
      //  获取选中的角色信息ID
      getSelectRoleIngo() {
        let arrTemp = [];
         const params =  { userId: this.userId }
        getParam('/userRoleRelation',params)
          .then(response => {
            if (response.data.code == 200) {
              console.log('获取选中的角色信息ID'+ response.data.data)
              for(let item of response.data.data)
                  arrTemp.push(item.id);
              }
              this.roleId = arrTemp
          })
      },
      // 分配用户与角色数据
      saveUserRoleRelation(userId) {
        let _th = this;
        let param = {
          ids: this.roleId,
          userId: userId
        }
       postParam('/userRoleRelation', param)
          .then(response => {
             if (response.data.code == 200) {
                success(response.data.msg, _th)
              } else {
                warning(response.data.msg, _th)
              }
              this.doQuery()
            this.dialogVisible = false
          })
      },
      // 修改用户与角色数据
      upUserRoleRelation(userId) {
        let _th = this;
        let param = {
          ids: this.roleId,
          userId: userId
        }
       putParam('/userRoleRelation', param)
          .then(response => {
             if (response.data.code == 200) {
                success(response.data.msg, _th)
              } else {
                warning(response.data.msg, _th)
              }
              this.doQuery()
            this.dialogVisible = false
          })
      },
      // 启用禁用
      isValid(index, row) {
        let state = row.state == 10 ? 20 : 10
        let _th = this
        var param = new URLSearchParams()
        param.append('state', state)
        param.append('userId', row.id)
        let loadingInstance = load.service({
          text: '请稍等',
          target: document.querySelector('.el-main') // 设置加载居于块
        })
        putParam('/changeState', param)
          .then(response => {
            if (response.data.code == 200) {
              success(response.data.msg, _th)
              this.doQuery()
            } else {
              warning(response.data.msg, _th)
            }
            this.dialogVisible = false
            loadingInstance.close()
          })
      },
      // 触发全选删除 选择框
      handleSelectionChange(val) {
        this.multipleSelection.length = 0
        for (let index in val) {
          this.multipleSelection.push(val[index].id)
        }
      },
      //handleSelectionChangeAllocation() {},
      // 重置  还有 分页插件做成 公共的
      doClean() {
        this.userName = null
        this.doQuery()
      },
      //新增用户
      handleOpenAdd() {
          this.user.loginName = null;
          this.user.password = null;
          this.user.realName = null;
          this.user.phoneNo = null;
          this.user.address = null;
          this.user.state = 10;
          this.isChange = false;
          this.dialogVisible = true; // 控制添加按钮弹出框
          this.userId = null; // 被分配的用户ID 临时赋值
          this.roleId = [];//清空角色组
      },
      //编辑用户
      handleEdit(index,row){
        this.roleId = [];
        this.user = row;
        this.isChange = true;//是否是编辑数据 判断提交路径
        this.dialogVisible = true; // 控制添加按钮弹出框
        this.userId = row.id; // 被分配的用户ID 临时赋值
        this.getSelectRoleIngo();

      },
      // 分页触发
      handleCurrentChange: function(currentPage) {
        this.page = currentPage
        this.doQuery()
      },
    },
    mounted() {
      this.doQuery();
      //初始化加载角色信息
      this.doGetRoleInfo();
    }
  }
</script>
