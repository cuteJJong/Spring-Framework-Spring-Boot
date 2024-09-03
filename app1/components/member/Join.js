import {useState} from "react"
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Join(){
    const navigate = useNavigate();
    const [inputs, setInputs] = useState({id:'', pwd:'', name:'', email:'', type:''})
    const {id, pwd, name, email, type} = inputs;
    const onChange = (e) => {
        const {name, value} = e.target;
        setInputs({
            ...inputs,
            [name]:value
        })
    }
    const join = () => {
        axios.post('http://localhost:8081/join',{},{params:{id:id, pwd:pwd, name:name,
            email:email,type:type}})
            .then(function(res){//res.status:상태값, res.data:백에서 보낸 데이터
                if(res.status === 200){
                    if(res.data.flag){
                        alert('회원가입 완료');
                        navigate('/userhome');
                    }else{
                        alert('회원가입 취소');
                    }
                }else{
                    alert('비정상 응답')
                }
            })
    }
    return(
        <div>
            <h3>join</h3>
            id:<input type="text" name="id" value={id} onChange={onChange}/><br/>
            pwd:<input type="password" name="pwd" value={pwd} onChange={onChange}/><br/>
            name:<input type="text" name="name" value={name} onChange={onChange}/><br/>
            email:<input type="text" name="email" value={email} onChange={onChange}/><br/>
            type:<input type="text" name="type" value={type} onChange={onChange}/><br/>
            <button onClick={join}>회원가입</button>
        </div>
    )
}