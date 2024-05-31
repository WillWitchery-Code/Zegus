<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <parallax
        class="page-header-image"
        style="background-image:url('img/header.jpg')"
      >
      </parallax>
      <div class="container">
        <div class="content-center brand">
          <img class="n-logo" src="img/zegus_1.png"/>
          <h1 class="h1-seo" 
          style="color:black; 
          font-weight:bold">
          ZEGÜS</h1>
          <h4 
          style="color: black;"     
          >Social Comunity Network</h4>  
          <div class="col text-center">
        <router-link
          to="/login"
          class="btn btn-info btn-round btn-lg btn-border 
          border: 10px solid black;"
          
        >
          JOIN
        </router-link>
        
      </div>
        </div>
        
        <h6 class="category category-absolute"
        style="text-align: left;">
          
          Based on
          <a href="http://invisionapp.com/" target="_blank">
            <img src="img/invision-white-slim.png" class="invision-logo" /> </a>        >.
        </h6>
      </div>
      
    </div>
   

  
    
  </div>

</template>
<script>
import { Parallax } from '@/components';
import BasicElements from './components/BasicElementsSection';
import Navigation from './components/Navigation';
import TabsSection from './components/Tabs';
import ProgressPagination from './components/ProgressPagination';
import Notifications from './components/Notifications';
import Typography from './components/Typography';
import JavascriptComponents from './components/JavascriptComponents';
import NucleoIconsSection from './components/NucleoIconsSection';
import ExamplesSection from './components/ExamplesSection';
import DownloadSection from './components/DownloadSection';

export default {
  name: 'index',
  bodyClass: 'index-page',
  components: {   
   
    
  }
};
</script>
<style></style>
