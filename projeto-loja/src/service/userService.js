import { api } from "./api"

export const HandleLogin = async (loginInfo) =>{
    const url = "usuarios/login"
    return api.post(url, loginInfo)
}

export const HandleRegister = async (registerInfo) =>{
    const url = "usuarios/criar"
    return api.post(url,registerInfo)
}

