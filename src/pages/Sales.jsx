import React from 'react'
import Sidebar from '../components/Sidebar'
const Sales = () => {
  return (
    <>
      <div className="home-page flex flex-row w-full min-h-screen">
        <Sidebar />
        <div className="ml-64 w-full bg-[#6b10743d]">
          <h1>Sales</h1>
        </div>
      </div>
    </>
  )
}

export default Sales