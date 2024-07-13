const signInBtnLink=document.querySelector('.signIpBtn-link');
const signUpBtnLink=document.querySelector('.signUpBtn-link');
const wrapper=document.querySelector('.wrapper');

signUpBtnLink.addEventListener('click',() => {
    wrapper.classList.toggle('active');
});

signInBtnLink.addEventListener('click',() => {
    wrapper.classList.toggle('active');
});
