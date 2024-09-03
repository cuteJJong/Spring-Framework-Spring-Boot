import { useNavigate } from "react-router-dom"

export default function ParamSrc(){
    const navigate = useNavigate();
    const go = () => {
        navigate('/dest', {state:{id:1, job:'개발자'}});
    }
    return(
        <div>
            <button onClick={go}>컴포넌트 이동</button>
        </div>
    )
}