import React from 'react'
import Sidebar from '../components/Sidebar'
import Container from '../components/Container'

const HomePage = () => {
    return (
        <>
            <div className="home-page flex flex-row w-full min-h-screen">
                <Sidebar />
                <Container />
            </div>

        </>
    )
}

export default HomePage