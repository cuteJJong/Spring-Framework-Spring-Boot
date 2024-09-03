import './App.css';
import MyComp from './components/MyComp';
import {Link, BrowserRouter} from "react-router-dom"
import Router from './Router';

function App() {
  return (
    <div className="App">
      <MyComp arg1="props 속성값"/>
      <hr/>
      <BrowserRouter>
        
        <Link to="/userhome">user home</Link> |  
        <Link to="/about/김유진?detail=가나다&mode=abc">about</Link> |  
        <Link to="/src">src</Link> |  
        <hr/>
        <Router/>
      </BrowserRouter>
    </div>
  );
}

export default App;
