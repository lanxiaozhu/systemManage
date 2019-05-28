<!-- 产品阅览-->
<template>
  <div class="d">
    <el-row v-for="(page, index) of pages" :key="index">
      <el-col :span="6" v-for="(item, innerindex) of page" :key="innerindex" :offset="innerindex > 0 ? 2 : 1">
        <!-- 图片 标签 title  下方文字为位置  价格  布局  -->
        <a :href="item.houseLink">
          <img :src="'http://localhost:8081/'+subStr(item.houseFilePath)" :title="item.houseTitle" class="img-wrap" />
          <div class="house_name">
            <p :show-overflow-tooltip="true">{{item.houseLocation}} <span style="font-size:12px">{{item.houseLayout ?  item.houseLayout : item.houseExt}}</span></p>
            <p>{{item.houseSumPrice}}万</p>
          </div>
        </a>
      </el-col>
    </el-row>
  </div>
</template>
<style>
  .d {
    width: 100%;
    height: 650px;
    overflow: auto;
    overflow-x: hidden;
  }
  .el-col-6 {
    width: 20%;
    height: 294.8px;
    margin: 15px 30px;
  }
  .goodhouse {
    position: absolute;
    right: 0;
    top: 0;
    background: url(/pegasus/redskull/images/ershoufang/goodhouse/goodhouse_rec_tag@2x.png?v=89fc5979) no-repeat 50%/72px;
    width: 72px;
    height: 30px;
  }
  .img-wrap {
    width: 265px;
    height: 205px;
    border-radius: 4px;
  }
  .house_name {
    float: left;
    /* margin-top: 16px; */
    font-size: 18px;
    font-weight: 700;
    line-height: 10px;
    text-align: left;
    color: #222;
  }
</style>
<script>
  import {
    getInfo,
    getParam,
    postParam,
    delParam,
    putParam
  } from '@/components/axios/api.js'
  export default {
    data() {
      return {
        data: []
      }
    },
    methods: {
      doQuery() {
        getInfo('/house/info')
          .then(response => {
            this.data = response.data
          })
      },
      subStr(str) {
        var rs = str.split(';');
        return rs[0];
      }
    },
    //计算函数
    computed: {
      //每页四张图片
      pages() {
        const pages = []//数据中转
        this.data.forEach((item, index) => {
          const page = Math.floor(index / 4) //4代表4条为一行，随意更改   向下取整
          if (!pages[page]) {
            pages[page] = []
          }
          pages[page].push(item)
        })
        return pages
      }
    },
    mounted() {
      this.doQuery()
    }
  }
</script>
