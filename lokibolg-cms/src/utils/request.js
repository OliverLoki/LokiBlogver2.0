import axios from 'axios';
import store from '../store'
import router from "../router";
import Element from 'element-ui'

axios.defaults.baseURL="http://localhost:8020"

// 前置拦截
axios.interceptors.request.use(config => {
    return config
})

axios.interceptors.response.use(response => {
        let res = response.data;

        console.log("=====进入拦截器============")
        console.log(res)
        console.log("=================")


        if (res.code == 200) {
            return response
        } else {
            Element.Message.error('密码错误', {duration: 3 * 1000})
            router.push("/admin")
            return Promise.reject(res.msg)

        }

    },
    error => {
        console.log(error+"哪里出错了")
        if(error.response.data) {//如果不为空
            error.message = error.response.data.msg
        }


        if(error.response.status == 400) {
            store.commit("REMOVE_INFO")
            router.push("/admin")
        }

        Element.Message.error(error.message, {duration: 3 * 1000})
        return Promise.reject(error)
    }
)


