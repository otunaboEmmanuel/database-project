import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import HomePage from './pages/HomePage'
import Management from "./pages/UserManagement"
import Categories from "./pages/Categories"
import Sales from "./pages/Sales"
import Products from "./pages/Products"
import Reports from "./pages/SalesReport"
import Login from "./pages/Login"

function App() {

  return (
    <>
      <Router>
        <Routes>
          <Route path='/dashboard' element={<HomePage />} />
          <Route path='/management' element={<Management />} />
          <Route path='/categories' element={<Categories />} />
          <Route path='/sales' element={<Sales />} />
          <Route path='/products' element={<Products />} />
          <Route path='/reports' element={<Reports />} />
          <Route path="/" element={<Login />} />
        </Routes>
      </Router>
    </>
  )
}

export default App
