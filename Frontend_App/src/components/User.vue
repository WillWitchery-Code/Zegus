<template>
    <div class= "container">
        <h1 class= "text-center"> User List </h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Email</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for= "user in user" :key = "user.id">
                    <td> {{user.e_mail}} </td>
                    <td> {{user.password}} </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import { UserService } from "../services/UserService.js";
   
    export default{
        name: "User",
        data(){
            return{
                user: []
            }
        },
        methods:{
            getUser(){
                UserService.getUser().then(response => {
                    this.user = response.data;
                });
            }
        },
    
        created(){
            this.getUser();
        }
    
}
</script>
        


