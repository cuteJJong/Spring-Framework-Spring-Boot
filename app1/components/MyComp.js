import {useState} from "react"

export default function MyComp(props){
    const [msg, setMsg] = useState('');
    const onChange = (e) => {
        setMsg(e.target.value);
        alert(msg);
    }
    return(
        <div>
            <h2>{props.arg1}</h2>
            msg:<input type="text" name="txt" value={msg} onChange={onChange}></input>
        </div>
    )
}