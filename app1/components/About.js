import { useSearchParams, useParams } from "react-router-dom";

export default function About() {
    const {username} = useParams();//패스에 작성한 값
    const [params] = useSearchParams(); //? 다음 작성한 파라메터 값들
    const detail = params.get("detail"); //params에서 키값으로 검색
    const mode = params.get("mode"); //params에서 키값으로 검색
    return (
        <div>
            <h2>About</h2>
            username:{username}<br/>
            detail:{detail}<br/>
            mode:{mode}<br/>
        </div>
    )

}
