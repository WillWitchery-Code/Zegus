<template>
  <div class="page-header clear-filter">
    <parallax
      class="page-header-image"
      style="background-image:url('img/background/edit.jpg')"
    ></parallax>

    <div class="container">
      <card class="signup" header-classes="text-center" color="blue">
        <div class="center-content">
          <h4>Feed</h4>
          <input type="text" class="form-control" placeholder="Share your Ideas here..." v-model="inputContent" />
        </div>
        <button @click="createContent" class="btn btn-info">Share your Zegüs</button>
      </card>

      <card class="signup" color="blue">
        <h5 v-for="(sharing, index) in sharings" :key="index">                                       
        {{ sharing }}
        </h5>
      </card>
    </div>
  </div>
</template>

<script>
import { Card } from '@/components';
import axios from 'axios'; 

export default {
  data() {
    return {
      inputContent: '',
      create_share: '',
      create_new_share: '',
      sharings: [],
      z: "has Zegüsqued:",
      u_script:"",
      user_owner: '',
      userData: {}
    };
  },
  components: {
    Card,
  },
  created() {
    const storedSharings = JSON.parse(localStorage.getItem('sharings'));
    this.userData = JSON.parse(localStorage.getItem('user_info'));
    this.create_new_share = JSON.parse(localStorage.getItem('url_new_shares'));
    
 
    if (storedSharings) {
      this.sharings = storedSharings;
    }
    this.userData = JSON.parse(localStorage.getItem('user_info')) || {};
  },
  methods: {
    async getContent() {
      try {
        const response = await axios.get(this.create_share);
        
        if (response.status === 200) {
          
          this.sharings = response.data.content; // Ensure this matches your backend response structure
          localStorage.setItem('sharings', JSON.stringify(this.sharings));
        }
      } catch (error) {
        console.error(error);
      }
    },

    async createContent() {
    try {
        
        this.u_script = this.userData.username + "   has Zegüsqued:   ";
        
        
        const response = await axios.post(this.create_new_share,
        { content: this.u_script + this.inputContent }) 
        
        if (response.status === 200) {
          
          this.sharings.push(response.data.content);
         ;
          
          
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style>
.Zcolor .z {
  color: yellow;
}
</style>