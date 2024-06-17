<template>
  <navbar
    position="fixed"
    type="primary"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
  <nav>
    <!-- Other navbar content -->

    <router-link
      v-if="$route.path == '/login' && $route.path == '/register'"
      to="/login"
      class="btn btn-simple btn-round btn-blue btn-lg"
    >
      Login
    </router-link>
  </nav>

    <template>
      <router-link
        to="/">
      <img 
      class="n-logo" 
      src="img/zegus_1.png"
      width="25" />
      </router-link>
      <span style="color: black;">
        beta_
      </span>
      
      <span style="color: black;">
        Zegüs
      </span>
      
    </template>
    <template slot="navbar-menu">

      <li>
        <nav-link ></nav-link>
        <router-link
        to="/canvas"
          class="nav-link btn btn-info"
         
        >
          <i class="now-ui-icons location_world"></i>
          <p>&nbsp &nbsp Canvas</p>
        </router-link>
      </li>   <p>&nbsp &nbsp</p>

      <li v-if="isLoggedIn">
        <nav-link ></nav-link>
        <router-link
        to="/Profile"
          class="nav-link btn btn-neutral"
         
        >
          <i class="now-ui-icons users_circle-08"></i>
          <p>&nbsp &nbsp Profile</p>
        </router-link>
      </li>


      <p>&nbsp &nbsp</p>
    
      <li v-if="isLoggedIn">
        <nav-link ></nav-link>
        <router-link
        to="/Edit_Profile"
          class="nav-link btn btn-neutral"
         
        >
          <i class="now-ui-icons education_glasses"></i>
          <p>&nbsp &nbsp Edit Profile</p>
        </router-link>
      </li>


      <p>&nbsp &nbsp</p>
      
      
      
     
      <li>
        <nav-link ></nav-link>
        <router-link
        to="/landing"
          class="nav-link btn btn-neutral"
         
        >
          <i class="now-ui-icons transportation_air-baloon"></i>
          <p>&nbsp &nbsp About Us</p>
        </router-link>
      </li>

      
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from '@/components';
import { Popover } from 'element-ui';
import axios from 'axios';
export default {
  name: 'main-navbar',
  props: {
    transparent: Boolean,
    colorOnScroll: Number
  },
  data() {
    return {
      isLoggedIn: false
    };
  },
  components: {
    Navbar,
    NavLink,
    [Popover.name]: Popover
  },
  created() {
    this.login_ver();
  },

  methods: {
    async login_ver() {
      if  (localStorage.getItem('loged_v') === "1") {
        this.isLoggedIn = true;
      }
    },

  },
 

};
</script>
<style scoped></style>
