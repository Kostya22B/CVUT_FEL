document.addEventListener('DOMContentLoaded', function(){
  document.getElementById('target').onclick = function(){
    let isdark = document.documentElement.classList.contains("dark");
    if (isdark) {
      document.documentElement.classList.remove('dark')
    } else {
      document.documentElement.classList.add('dark')
    }
  }
})