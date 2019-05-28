<template>
        <el-container >
            <el-aside style="width: 14%;"><navMenu/></el-aside>
        <el-container >
            <el-header><headerPage/></el-header>
            <el-main> <router-view></router-view></el-main>
        </el-container>

        </el-container>
</template>

<script>
// 引入组建
import navMenu from '@/components/sys/navMenu'
import headerPage from '@/components/sys/headerPage'    

export default {
  components: {navMenu, headerPage }
}
</script>

<style>
    body{
        margin:0px;
        padding:0px;
    }
    .el-aside{
       height: 100VH;
       background-color: #2d3a4b;
    }
    .el-header{
        background-color:#F0FFFF;
        text-align: center;
        line-height: 44px;
    }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    padding:0px;
  }

</style>
