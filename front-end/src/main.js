import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from '@/App.vue';
import router from "@/router";
import global from './Global'
import store from "@/store";
import {vueInstance} from "@/common/myAxios";
import VueResource from 'vue-resource'

Vue.config.productionTip = false
Vue.prototype.global = global;
Vue.use(ElementUI);
Vue.use(VueResource);

let instance = new Vue({
    el: "#app",
    router,
    store,
    created() {
        // Prevent blank screen in Electron builds
        this.$router.push('/login')
    },
    render: h => h(App)
});

vueInstance.instance = instance;
vueInstance.store = instance.$store;