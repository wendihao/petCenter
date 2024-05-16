<template>
<div class="container">这是移动端首页</div>
</template>
<script>
export default {}
</script>
<style scoped>
  .header{
    display: flex;
    justify-content: space-between;
    justify-items: center;
  }
  .header-left{
    display: flex;
  }
  .header-right{
    display: flex;
  }
  .footer{
    display: flex;
    justify-content: space-around;
  }
  .footer-footer{
    display: flex;
    justify-content: center;
  }
  .p-overflow{
    font-size: x-small;
    overflow:hidden;
    text-overflow:ellipsis;
    display:-webkit-box;
    -webkit-box-orient:vertical;
    -webkit-line-clamp: 2;
  }
  .footer-right{
    display: flex;
  }
  .footer-left{
    display: flex;
    text-align: center;
  }
  .el-divider--vertical {
    display: inline-block;
    width: 1px;
    height: 10em;
    margin: 0 8px;
    vertical-align: middle;
    position: relative;
  }
  /*<!--container-->*/
  .el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
    text-align: center;
  }
  .el-footer{
    background: #363b384f;
    text-align: center;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 800px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    background-color: #409EFF;
    color: #333;
    text-align: center;
    height: 850px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  /*Layout*/
  /*走马灯*/
  /*下拉菜单*/
  .el-dropdown-link {
    color: #409EFF;
  }
  .el-icon-arrow-down {
  }
  .blog-title:hover{
    cursor: pointer;
  }
   .el-menu-vertical-demo{
     height: 100%;
   }
</style>
