import React from 'react'
import Sidebar from '../components/Sidebar'
const SalesReport = () => {
  return (
    <>
      <div className="home-page flex flex-row w-full min-h-screen">
        <Sidebar />
        <div className="ml-64 w-full bg-[#f4f4f4]">
          <h1>SalesReport</h1>
        </div>
      </div>
    </>
  )
}

export default SalesReport