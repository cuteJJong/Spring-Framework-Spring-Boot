
import UserHome from "./components/UserHome"
import {Routes, Route} from "react-router-dom"
import About from "./components/About"
import ParamSrc from "./components/ParamSrc"
import ParamDest from "./components/ParamDest"
import NotFound from "./components/NotFound"
import Join from "./components/member/Join"

export default function Router(){
    return(
        <Routes>
            <Route path="/userhome" element={<UserHome/>}/>
            <Route path="/about/:username" element={<About/>}/>
            <Route path="/src" element={<ParamSrc/>}/>
            <Route path="/dest" element={<ParamDest/>}/>
            <Route path="/member/join" element={<Join/>}/>
            <Route path="*" element={<NotFound/>}/>
        </Routes>
    )
}