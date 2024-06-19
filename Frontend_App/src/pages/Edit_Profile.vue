<template>



  <div class="section section-signup"
  
  style="background-image: url('img/background/loginh.jpg'); background-size: cover; background-position: top center; min-height: 400px;"
  >
  
      
  <div class="profi">
    <Profile_Put> </Profile_Put>
  </div>
  </div> 
</template>

  
<script>
  import Profile_Put from './components/Profile_Put.vue';
  import { Card, Button, FormGroupInput } from '@/components';

    export default {
      components: {
        Profile_Put,
        
        [Button.name]: Button,
        [FormGroupInput.name]: FormGroupInput
      }
    };
</script>

<style> 

.profi {
  place-items: center;
  position: relative;
  right: 200px;
  top: -140px;
}


</style>
  