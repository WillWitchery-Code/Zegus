<template >
    <div class="section section-tabs">

    <div class="container" >
      <div class="title">
        <p>&nbsp</p>
      </div>

        <div class="row">
            <div class="col-md-10 ml-auto col-xl-6 mr-auto">
       
       

            <card class="card-signup"
                header-classes="text-center" 
                color="blue"
            >                    
                <tabs
                    slot="raw-content"
                    tab-content-classes="tab-content-padding text-center"                   
                >
                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons users_single-02"></i> Profile
                        </template>
                            <div class="input-container">                                      
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.name ? userData.personalInfo.name : 'Enter Name'"
                                v-model="data.name">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.last_name ? userData.personalInfo.last_name : 'Enter Last Name'" 
                                v-model="data.last_name">
                            </div>   
                            <div class="input-container">                                      
                                <input type="text" class="form-control"
                                
                                :placeholder="userData.personalInfo.dob ? userData.personalInfo.dob : 'Enter Date of Birth'"
                                v-model="data.dob">
                             
                            </div>                          
                            
                        <button @click="updateUserData" class="btn btn-info btn-round" color="orange">Update Profile</button>
                    </tab-pane>
                    
                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons users_single-02"></i> Contact
                        </template>

                        <div class="input-container"> 
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.phone_number ? userData.personalInfo.phone_number : 'Enter Phone Number'"
                                v-model="data.phone_number">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.e_mail ? userData.personalInfo.e_mail : 'Enter E-Mail'"
                                v-model="data.e_mail">
                            </div>
                        
                            <button @click="updateUserData" class="btn btn-info btn-round">Update Contact</button>

                    </tab-pane>

                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons users_single-02"></i> Profession
                        </template>                 
                            <div class="input-container"> 
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.profession ? userData.personalInfo.profession : 'Profession'"
                                v-model="data.profession">
                                
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.education ? userData.personalInfo.education : 'Education'"
                                v-model="data.education">
                            </div>                       
                            <div class="input-container"> 
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.description ? userData.personalInfo.profession : 'About Me'"
                                v-model="data.description">                            
                            </div>
                        <button @click="updateUserData" class="btn btn-info btn-round" color="orange">Update Proffesion</button>
                        </tab-pane>

                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons users_single-02"></i> Your Space
                        </template>
                                    
                            <div class="input-container"> 
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.link1 ? userData.personalInfo.link1 : 'Enter a personal Link'"
                                v-model="data.link1">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.link2 ? userData.personalInfo.link2 : 'Enter a personal Link'"
                                v-model="data.link2">
                            </div>
                            <div class="input-container">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.link3 ? userData.personalInfo.link3 : 'Enter a personal Link'"
                                v-model="data.link3">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.link4 ? userData.personalInfo.link4 : 'Enter a personal Link'"
                                v-model="data.link4">
                            </div>
                        <button @click="updateUserData" class="btn btn-info btn-round" color="orange">Update Links</button>
                    </tab-pane>

                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons users_single-02"></i> Location
                        </template>                                         
                            <div class="input-container">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.country ? userData.personalInfo.country : 'Enter Country'"
                                v-model="data.country">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.city? userData.personalInfo.city : 'Enter City'"
                                v-model="data.city">
                            </div>        
                            <div class="input-container">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.address? userData.personalInfo.address : 'Enter Address'"
                                v-model="data.address">
                                <input type="text" class="form-control" 
                                :placeholder="userData.personalInfo.zip_code ? userData.personalInfo.zip_code : 'Enter Postal Code'"
                                v-model="data.zip_code"
                                >
                            </div>
                            <button @click="updateUserData" class="btn btn-info btn-round" color="orange">Update Location</button>
                    </tab-pane>
                              
                    
                    <tab-pane>
                        <template slot="label">
                            <i class="now-ui-icons objects_key-25"></i> Password
                        </template>                            
                            <div class="input-container2"> 
                                <input type="text" class="form-control" placeholder="Old Password"
                                v-model="data.oldpass1">                                
                            </div>
                            <div class="input-container2"> 
                                <input type="text" class="form-control" placeholder="Old Password"
                                v-model="data.oldpass2">
                            </div>
                            <div class="input-container2">                      
                                <input type="text" class="form-control" placeholder="New Password"
                                v-model="data.password">  
                            </div>
                            
                        <button @click="updatePassword" class="btn btn-info btn-round">Update Password</button>
                    </tab-pane> 
                </tabs> </card>
                
            </div>
            </div>  </div>
    </div>
         
</template>
          
<script>
      import { Card, Tabs, TabPane } from '@/components';
      import {Button, FormGroupInput } from '@/components';
      import axios from 'axios';

      export default {
        
        components: {
    
          Card,

          Tabs,
          TabPane,
        },
        
  data() {
    return {
      data: {
        name: '',
        last_name: '',
        dob: '',
        phone_number: '',
        e_mail: '',
        profession: '',
        education: '',
        description: '',
        link1: '',
        link2: '',
        link3: '',
        link4: '',
        country: '',
        city: '',
        address: '',
        zip_code: '',
        oldpass1: '',
        oldpass2: '',
      },
       
        
    };
    
  },
  created() {
    this.userData = JSON.parse(localStorage.getItem('user_info'));
    this.urlUserData = JSON.parse(localStorage.getItem('user_data_url'));
    
    
    this.veri = JSON.parse(localStorage.getItem('loged_v'));

  },
  methods: {
    
    async updateUserData() {
      try {
        const token = localStorage.getItem('token');
        const url = this.urlUserData;

        let name = {...this.personalInfo, 
                    name: this.data.name, 
                    last_name: this.data.last_name, 
                    phone_number: this.data.phone_number, 
                    e_mail: this.data.e_mail, 
                    dob: this.data.dob,
                    profession: this.data.profession,
                    education: this.data.education,
                    description: this.data.description,
                    link1: this.data.link1,
                    link2: this.data.link2,
                    link3: this.data.link3,
                    link4: this.data.link4,
                    country: this.data.country,
                    city: this.data.city,
                    address: this.data.address,
                    zip_code: this.data.zip_code,};
        
        
     
        const response = await axios.put(url, name, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });


        if (response.status === 200) {
          alert('User updated successfully');

          localStorage.setItem('personalInfo', JSON.stringify(this.name));
         
        }
      } catch (error) {
        console.error(error); 
      };    
  },

  async updatePassword() {
        try {
          
          this.passw = JSON.parse(localStorage.getItem('passw'));
          this.urlUser = JSON.parse(localStorage.getItem('user_url'));
          const url2 = this.urlUser;
          const token = localStorage.getItem('token');
        

          let Upassword = {...this.users, password: this.data.password};
          
            if (this.data.oldpass1 == "" || this.data.oldpass2 == "") {
                alert("Put your old password two times");
            } else {

                    if (this.data.oldpass1 !== this.data.oldpass2) {
                    alert("Old passwords does not match");
                    } else {
                        if (this.data.oldpass1 != this.passw
                        && this.data.oldpass2 != this.passw
                            ) {
                        alert('Old passwords does not match with the current password');
                            
                        }

                    }

            }
                
            if (this.data.oldpass1 == this.passw && 
                this.data.oldpass2 == this.passw) {

                    const response = await axios.put(url2, Upassword, {
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`,
                        },
                        });
              
            if (response.status === 200) {
                alert('Password updated successfully');
                localStorage.setItem
            }

        } else {
        }} catch (error) {console.error(error); 
    };    
  },


  
  },
        
      };
</script>

<style>
    .input-container {
        display: flex; 
        gap: 10px;
        justify-content: center;
        align-items: center;
    }

    .input-container2 {
        gap: 10px;
        align-items: center;
        justify-content: center;
        display: flex;

    }
</style>
      