<template >
  <div class="page-header clear-filter" filter-color="blue">
    <div
      class="page-header-image"
      style="background-image: url('img/background/loginh.jpg')"
    ></div>

    
    
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
 
 
  <card 
  class="card-signup"
  header-classes="text-center" 
  color="blue"

  border-radius="20px"
  style="position: absolute; right: 400px; top: -40px; width: 200px; height: 400px;"
          >

          <template>
        
          <fg-input
              class="no-border"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="Usrename"
              type="username" 
              v-model="data.username"
            >
          </fg-input>
            

            <fg-input
              class="no-border input-lg-padding"
              addon-left-icon="now-ui-icons objects_key-25"
              placeholder="Password"
              type="password" 
              v-model="data.password"
            >
          </fg-input>
          
          <br>
          <br>
          <br>

            <button 
            @click="login"
            class="btn btn-info btn-round btn-lg "
            >
            Login</button>
      
           <br>

            <button 
            @click="register"
            class="btn btn-warning btn-round btn-lg "
            >
            Sign In</button>



          </template>

         
  </card>

  <card 
  class="card-login2"
  header-classes="text-center" 
  color="blue"

  border-radius="20px"
  style="position: absolute; right: 400px; top: -40px; width: 500px; height: 400px;"
          >

          <template>
        
          <fg-input
              class="no-border"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="Usrename"
              type="username" 
              v-model="data.username"
            >
          </fg-input>
            

            <fg-input
              class="no-border input-lg-padding"
              addon-left-icon="now-ui-icons objects_key-25"
              placeholder="Password"
              type="password" 
              v-model="data.password"
            >
          </fg-input>
          
          <br>
          <br>
          <br>

            <button 
            @click="login"
            class="btn btn-info btn-round btn-lg "
            >
            Login</button>
      
           <br>

            <button 
            @click="register"
            class="btn btn-warning btn-round btn-lg "
            >
            Sign In</button>



          </template>

         
  </card>
          
          
        </div>
        <div>
         
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
import SignupForm from './components/SignupForm.vue';

export default {
  data() {
    return {
        data: {
          username: '', 
          password: ''
      },
            userData: {
              name: '',
              e_mail: '',
            },
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
        }   
      );

        if  (response.status === 200) {         
                 
          const user = response.data.user;
          localStorage.setItem('user_info', JSON.stringify(user));

          const url_user = response.data.url_user;
          localStorage.setItem('user_url',JSON.stringify(url_user));

          const url_user_data = response.data.url_user_data;
          localStorage.setItem('user_data_url',JSON.stringify(url_user_data));

          const passw = this.data.password;
          localStorage.setItem('passw', JSON.stringify(passw));

          localStorage.setItem('loged_v', '1');
                        
          this.$router.push('/profile');
              
        }


        else {
            alert('Login failed');
        }
        } catch (error) {
            console.error(8081);
            alert('Bad Username or Password');
    }
  },
 
  async register() {
      try {
        const response = await axios.post('http://127.0.0.1:7777/register', {
          username: this.data.username,
          password: this.data.password,
        });
        if (response.data.msg === "User created") {
          console.log("User registered successfully")
          this.$router.push('/profile');
        } else {
          console.log("Registration failed");
          // Handle error during registration
        }
        } catch (error) {
            console.error(409);
            alert('this Username already exists, please try another one');           
        }
    
    
    }
  },
};
</script>
<style></style>
