<template>
  <div>
    <el-row>
      <el-col :span="12">
        <!--面包屑-->
        <span class="breadcrunb">
                    <el-breadcrumb separator="/" class="rumb">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item v-for="(item, index) in $route.meta" :key="index">{{item}}</el-breadcrumb-item>
                    </el-breadcrumb>
              </span>
      </el-col>
      <el-col :span="12">
        <!--  <span>
             <el-badge :value="12" class="item" style="float:right">
              <el-button size="small">未读消息</el-button>
            </el-badge>
          </span>-->
         
        <el-dropdown @command="handleCommand" class="drop">
          <img :src="imgUrl" class="avator">
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="home">首页</el-dropdown-item>
            <el-dropdown-item command="singout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

          <span class="name">
            你好：{{userName}}
          </span>
          
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        imgUrl: require('@/assets/logo.png'),
        userName: localStorage.getItem('userName'),
      }
    },
    methods: {
      handleCommand(command) {
        if (command == 'home') {
          this.$router.push('/')
        } else {
          axios.get('http://localhost:8088/login/logout')
            .then(function(response) {
              if (response.data.code == 200) {
                alert(response.data.msg)
                localStorage.removeItem('Authorization');
              } else {
                alert(response.data.msg)
              }
            })
            .catch(function(error) {
              console.log(error)
              alert('网络错误，不能访问')
            })
          this.$router.push('/')
        }
      },
    }
  }
</script>

<style>
  .rumb {
    margin-top: 20px;
    font-family: "Microsoft YaHei";
    background-color: aliceblue;
    display: inline;
    /*让两个div在一行*/
    float: left;
  }
  .name {
    margin-top: 0px;
    display: inline;
    /*让两个div在一行*/
    float: right;
  }
  .drop {
    margin-top: 0px;
    display: inline;
    /*让两个div在一行*/
    float: right;
  }
   
  .avator {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    /* 正方形图片 变成原形 */
  }
</style>
