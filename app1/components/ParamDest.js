import { useLocation } from "react-router-dom"

export default function ParamDest(){
    const loc = useLocation();
    const id = loc.state.id;
    const job = loc.state.job;
    return(
        <div>
            <h3>dest 컴포넌트</h3>
            id:{id}<br/>
            job:{job}<br/>
        </div>
    )
}