import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    //属性
    state: {
        token: '',
        userInfo: JSON.parse(sessionStorage.getItem("userInfo"))
    },
    //getset方法
    mutations: {
        //set
        SET_TOKEN: (state, token) => {
            state.token = token
            //存入loaclstorage
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo=userInfo
            //这个只能存储字符串，不能存储对象
            sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
        },
        REMOVE_INFO:(state)=>{
            state.token=''
            state.userInfo={}
            //本地存储空间
            localStorage.setItem("token",'')
            //会话存储空间
            sessionStorage.setItem("userInfo",JSON.stringify(''))
        }
    },
    getters: {
        //get
        getUser:state=>{
            return state.userInfo
        }
    },
    actions: {},
    modules: {}
})
