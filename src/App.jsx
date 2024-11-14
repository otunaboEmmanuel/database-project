// import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
// import HomePage from './pages/HomePage'
// import Sales from "./pages/Sales"
// import Reports from "./pages/Reports"
// import Login from "./pages/Login"
// import Inventory from "./pages/Inventory"
// import Service from "./pages/Service"
// import UserManagement from "./pages/UserManagement"
// import Profile from "./pages/Profile"
// import Settings from "./pages/Settings"
// function App() {

//   return (
{/* <>
  <Router>
    <Routes>
      <Route path='/dashboard' element={<HomePage />} />
      <Route path='/inventory' element={<Inventory />} />
      <Route path='/management' element={<UserManagement />} />
      <Route path='/sales' element={<Sales />} />
      <Route path='/service' element={<Service />} />
      <Route path='/reports' element={<Reports />} />
      <Route path='/profile' element={<Profile />} />
      <Route path='/settings' element={<Settings />} />
      <Route path="/" element={<Login />} />
    </Routes>
  </Router>
</> */}
//   )
// }

// export default App
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import HomePage from './pages/HomePage'
import Sales from "./pages/Sales"
import Reports from "./pages/Reports"
import Login from "./pages/Login"
import Inventory from "./pages/Inventory"
import Service from "./pages/Service"
import UserManagement from "./pages/UserManagement"
import Profile from "./pages/Profile"
import Settings from "./pages/Settings"

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path='/dashboard' element={<HomePage />} />
          <Route path='/inventory' element={<Inventory />} />
          <Route path='/management' element={<UserManagement />} />
          <Route path='/sales' element={<Sales />} />
          <Route path='/service' element={<Service />} />
          <Route path='/reports' element={<Reports />} />
          <Route path='/profile' element={<Profile />} />
          <Route path='/settings' element={<Settings />} />
          <Route path="/" element={<Login />} />
        </Routes>
      </Router>
    </>
  );
};

export default App;