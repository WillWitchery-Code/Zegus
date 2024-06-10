<template>
  <div>
    <div class="page-header clear-filter" filter-color="blue">
      <parallax
        class="page-header-image"
        style="background-image:url('img/bg5.jpg')"
      >
      </parallax>
      <div class="container">
        <div class="photo-container">
          <img src="img/ryan.jpg" alt="" />
        </div>
        <h3 class="title">{{ userData.username }}</h3>
        <h3 class="title">{{ userData.id_personal_info.name }}</h3>
        <p class="category">{{ userData.id_personal_info.e_mail }}</p>
        <p class="category">Photographer</p>
      
        <input
              class="no-border"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="Usrename"
              type="username" 
              v-model="data.username"
             
            >
          </input>

        <button @click="updateUserData" class="btn btn-primary btn-round">update</button>
       
      </div>
      
    </div>
    <div class="section">
      <div class="container">
        <div class="button-container">
          <a @click="alertUserData" href="#button" class="btn btn-primary btn-round btn-lg">Follow</a>
          <a
            href="#button"
            class="btn btn-default btn-round btn-lg btn-icon"
            rel="tooltip"
            title="Follow me on Twitter"
          >
            <i class="fab fa-twitter"></i>
          </a>
          <a
            href="#button"
            class="btn btn-default btn-round btn-lg btn-icon"
            rel="tooltip"
            title="Follow me on Instagram"
          >
            <i class="fab fa-instagram"></i>
          </a>
        </div>
        <h3 class="title">About me</h3>
        <h5 class="description">
          An artist of considerable range, Ryan — the name taken by
          Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs and
          records all of his own music, giving it a warm, intimate feel with a
          solid groove structure. An artist of considerable range.
        </h5>
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
  </div>
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
  
      },
        user:{
          _id: '',
        username: '',
        password: '',
        rol: null,
        id_personal_info: {
          _id: '',
          name: '',
          e_mail: '',
        
        }},
        
    };
    
  },
  created() {
    this.userData = JSON.parse(localStorage.getItem('user_info'));
    this.urlUserData = JSON.parse(localStorage.getItem('user_url'));

  },

  methods: {
    alertUserData() {
      alert(JSON.stringify(this.userData.username));
    },

    async updateUserData() {
      try {
        const token = localStorage.getItem('token');
        const url = this.urlUserData;

        let updatedUser = {...this.user, username: this.data.username};
        
     
        const response = await axios.put(url, updatedUser, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });


        if (response.status === 200) {
          alert('User updated successfully');
          this.user.username = this.data.username;
          localStorage.setItem('user', JSON.stringify(this.user));
        }
      } catch (error) {
        console.error(error); 
      };
  },
  },

};
</script>
<style></style>
