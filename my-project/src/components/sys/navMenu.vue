<template>
  <div class="menu">
    <el-menu default-active="intro" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" background-color="#2d3a4b" text-color="#fff" active-text-color="#ffd04b" router>
    <!-- 首页 -->
      <el-menu-item index="intro">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>

      <el-submenu v-for="(item,index) in mune" :index="''+index" :key="item.id">
        <template slot="title" v-if="item.menuLever==1">
            <i :class="item.menuIcon"></i>
            <span>{{item.menuName}}</span>
        </template>
        <el-menu-item-group v-for="(items) in item.menuList" :key="items.id">
          <el-menu-item :index="items.menuUrl"> <i :class="items.menuIcon"> </i>{{items.menuName}}</el-menu-item>
        </el-menu-item-group>
          
      </el-submenu>
    </el-menu>

</div>

</template>

<script>
  import axios from 'axios'
  import {
    getInfo
  } from '../axios/api.js'
  export default {
    data() {
      return {
        mune: {}
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath)
      },
      doQuery() {
        getInfo('menu/qeuryAll').then(res => {
          if (res.data.code == '200') {
            this.mune = res.data.data
          }
        });
      }
    },
    mounted() {
      this.doQuery()
    }
  }
</script>

<style>

</style>
