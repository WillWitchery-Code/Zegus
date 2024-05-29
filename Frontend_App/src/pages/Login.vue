<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>

    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>

            <div slot="header" class="logo-container">
              <img v-lazy="'img/now-logo.png'" alt="" />
            </div>
          
  
            <input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="Usrename"
              type="username" 
              v-model="data.username"
            >
           

            <input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="Password"
              type="password" 
              v-model="data.password"
            >
       

            <button 
            @click="login"
            class="btn btn-primary btn-round btn-lg btn-block"
            >
            Login</button>



           

            <template slot="raw-content">
              <div class="pull-left">
                <h6>
                  <a href="#pablo" class="link footer-link">Create Account</a>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">Need Help?</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>


<script>
import { Card, Button, FormGroupInput } from '@/components';
import MainFooter from '@/layout/MainFooter';
import axios from 'axios';

export default {
  data() {
    return {
        data: {username: '', password: ''
      }
    };

  },
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput
  },
  methods: {
    async login() {
     try {
        const response = await axios.post('http://127.0.0.1:7777/login', {
          username: this.data.username,
          password: this.data.password,
        });

        if  (response.data.msg === 'Token Created') {
              localStorage.setItem('token', response.data.token);
              this.$router.push('/profile');
        } else {
            alert('Login failed');
        }
        } catch (error) {
            console.error(error);
            alert('An error occurred');
    }
  }
  }
};
</script>
<style></style>
