<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <nav>
        <ul>
          <img 
      class="n-logo" 
      src="img/icon/CO.png"
      width="30" />
      <a>&nbsp&nbsp&nbsp&nbsp</a>
      <img 
      class="n-logo" 
      src="img/icon/github_ico.png" 
      width="30"/>
        </ul>
      </nav>
      <div class="copyright">
        &copy; {{ year }}, Based on
        <a href="https:///www.invisionapp.com" target="_blank" rel="noopener"
          >Invision</a
        >. Coded by
        <a href="https://binarcode.com" target="_blank" rel="noopener"
          >Will</a>
     
        
      </div>
     
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String
  },
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>
<style></style>
