<template>
  <div><div></div>
    
    <div class="page-header clear-filter " filter-color="blue">
      <img :src= "Cover_img" alt="Cover Picture"
        class="page-header-image"   
      >
      

      <div class="container">
        <div class="photo-container">
          <img :src="Profile_img" alt="Profile Picture">
        </div>
        <h3 class="title">{{ userData.username }}</h3>
        <h3 class="title">{{ userData.personalInfo.name + "&nbsp"}}{{"&nbsp" + userData.personalInfo.last_name }}</h3>
        <p class="category">{{ userData.personalInfo.e_mail }}</p>
        <p class="category">{{ userData.personalInfo.profession }}</p>
        <p class="category">{{ userData.personalInfo.city }}</p>
      
      </div>
      
    </div>
    <div class="page-background custom-bg-color">
    <div class="section ">
      <div class="container">
        <div class="button-container">
          <a @click="alertUserData" href="#button" class="btn btn-primary btn-round btn-lg">Follow</a>
          
        </div>
        <h3 class="title">About Me</h3>
        <h5 class="description">
          {{ userData.personalInfo.description }}
        </h5>
        <h3 class="title">Social Media Links</h3>
        <p align="center">
          {{ userData.personalInfo.link1}}
        </p>
        <p align="center">
          {{ userData.personalInfo.link2}}
        </p>
        <p align="center">
          {{ userData.personalInfo.link3}}
        </p>
        <p align="center">
          {{ userData.personalInfo.link4}}
        </p>
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">My Portfolio</h4>
          </div>
          <tabs
            pills
            class="nav-align-center"
            tab-content-classes="gallery"
            tab-nav-classes="nav-pills-just-icons"
            type="primary"
          >
            <tab-pane title="Profile">
              <i slot="label" class="now-ui-icons design_image"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg6.jpg" class="img-raised" />
                    <img src="img/bg11.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>

            <tab-pane title="Home">
              <i slot="label" class="now-ui-icons location_world"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg1.jpg" alt="" class="img-raised" />
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>

            <tab-pane title="Messages">
              <i slot="label" class="now-ui-icons sport_user-run"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                    <img src="img/bg6.jpg" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>
          </tabs>
        </div>
      </div>
    </div>
  </div></div>
</template>
<script>
import axios from 'axios';
import { Tabs, TabPane } from '@/components';




export default {
  name: 'profile',
  bodyClass: 'profile-page',
  components: {
    Tabs,
    TabPane
  },

  data() {
    return {
      data: {
        username: '',
        password: '',
        urlUserMedia: null,
        profilePictureSrc: ''
  
      },
        user:{
          _id: '',
        username: '',
        password: '',
        rol: null,
        personalInfo: {
          _id: '',
          name: '',
          e_mail: '',
        
        }},
        profilePictureSrc: '',
        
    };
    
  },
  created() {
    this.userData = JSON.parse(localStorage.getItem('user_info'));
    
    this.urlUserData = JSON.parse(localStorage.getItem('user_data_url'));
    this.urlUser = JSON.parse(localStorage.getItem('user_url'));
    
    this.Profile_img = JSON.parse(localStorage.getItem('url_profile_img'));
    this.Cover_img = JSON.parse(localStorage.getItem('url_cover_img'));
    
    this.veri = JSON.parse(localStorage.getItem('loged_v'));
    

    
  },

  methods: {

    
   
   
   
    
  }

 

};
</script>
<style>
.custom-bg-color {
  background-color: #D3D3D3; /* Change this to your desired background color */
}
</style>
