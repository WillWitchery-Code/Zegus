<template>
  <div>
 
    <div class="page-header page-header-small">
      <parallax
        class="page-header-image"
        style="background-image: url('img/background/land.jpg')"
      >
      </parallax>
      <div class="content-center">
        <div class="container">
          <h1 class="title" textcolor="black">This is Zegüs...</h1>
          
        </div>
      </div>
    </div>
    <div class="section section-about-us">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ml-auto mr-auto text-center">
            <h2 class="title">Who we are?</h2>
            <h4 >
              Zegüs is a vibrant online platform designed to foster connection and collaboration among diverse communities of artists, educators, outdoor enthusiasts, and individuals seeking to share their passions and insights with others. Our inclusive social network welcomes creators of all kinds, from painters and musicians to educators and adventurers.
            </h4>
          </div>
        </div>
        <div class="separator separator-primary"></div>
        <div class="section-story-overview">
          <div class="row">
            <div class="col-md-6">
              <div
                class="image-container image-left"
                style="background-image: url('img/landing/bg13.jpg')"
              >
                <!-- First image on the left side -->
                <p class="blockquote blockquote-primary">
                  "Knowledge of others is wisdom; knowledge of oneself is enlightenment."
                  <br />
                  <br />
                  <small>Lao Tzu</small>
                </p>
              </div>
              <!-- Second image on the left side of the article -->
              <div
                class="image-container"
                style="background-image: url('img/landing/bg11.jpg')"
              ></div>
            </div>
            <div class="col-md-5">
              <!-- First image on the right side, above the article -->
              <div
                class="image-container image-right"
                style="background-image: url('img/landing/bg12.jpg')"
              ></div>
              
              <h5>
                Zegüscua, derived from the Chibcha language, embodies the essence of storytelling and communication. Its significance goes beyond mere narration; it symbolizes the profound human need to connect, share, and learn from one another. In the context of our event, it serves as a poignant reminder of the importance of human interaction.
              </h5>
              <h5>
                At Zegüs, we believe in the power of shared experiences and knowledge to inspire, educate, and enrich lives. Whether you're an artist looking to showcase your latest masterpiece, a teacher eager to exchange lesson plans and teaching strategies, a hiker seeking adventure companions, or simply someone with a wealth of expertise to share, Zegüs provides a welcoming space free from intrusive advertisements.
              </h5>
             
              <h5>
                Join our thriving community today and embark on a journey of discovery, creativity, and connection. Together, let's build a world where everyone's voice is valued and celebrated.
              </h5>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
  </div>
</template>
<script>
import { Button, FormGroupInput } from '@/components';
export default {
  name: 'landing',
  bodyClass: 'landing-page',
  components: {
    
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput
  },
  data() {
    return {
      form: {
        firstName: '',
        email: '',
        message: ''
      }
    };
  }
};
</script>

<style scoped>
.section-about-us {
  background-color: #ddeffa;
}
</style>
