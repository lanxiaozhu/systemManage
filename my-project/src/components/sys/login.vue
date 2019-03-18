<template>
    <div class="login">
            <h3 class="title">后台管理系统</h3>
            <el-form :model="form" label-width="80px" >
                <el-form-item label="">
                 <el-input v-model="form.userName" placeholder="请输入用户名" style="width:250px"/>
              </el-form-item>
            <el-form-item label="">
                 <el-input type = "password" v-model="form.password" placeholder="请输入密码" style="width:250px"/>
            </el-form-item>
                <el-button type="primary" plain @click="submit">登陆</el-button>
           </el-form>

    </div>
</template>

<script>
//1 
import { mapMutations } from 'vuex';

export default {
  data () {
    return {
      userToken: null,
      form: {
        userName: null,
        password: null
      }
    }
  },
  methods: {
    ...mapMutations(['changeLogin']),
    submit: function () {
      let _this = this;
      this.axios
        .post('login/auth', this.form)
        .then(response => {
          if (response.data.code == 200) {
            console.log('登陆成功' + response.data.sessionId)

            this.userToken = response.data.sessionId
            console.log('获取到的token;' + this.userToken)// 获取到的token

           _this.changeLogin({ Authorization: this.userToken });
            this.$router.push('/intro')
          } else {
            alert(response.data.msg)
          }
        })
        .catch(error => {
          console.log(error)
          alert('网络错误，不能访问')
        })
    }
  }
}
</script>
<style>
body {
  text-align: center; /*全部内容居中*/
  background-color: #2d3a4b;
  width: 100%;
  height: 100%;
  /*background-image:url(../../assets/back.jpg);
     background-repeat:no-repeat;
     background-size:100%;/*这样设置图片就不会随屏幕缩放而改变了。（推荐）*/
}
.login {
  width: 400px;
  margin: 120px auto 40px auto;
}
.title {
  color: aliceblue;
  font-size: 26px;
  margin: 120px auto 40px auto;
}
.el-form-item {
  margin: auto 400px auto auto;
  padding: 2%;
}
.el-input__inner {
  background: rgba(0, 0, 0, 0.1);
  color: aliceblue;
}
</style>
