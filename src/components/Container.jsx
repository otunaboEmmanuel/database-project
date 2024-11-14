import React from 'react';
// Assuming you want to use Chart.js for charts

const Container = () => {
  // Sample data for the chart
  const data = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
      {
        label: 'Sales',
        fill: false,
        lineTension: 0.1,
        backgroundColor: 'rgba(75,192,192,0.4)',
        borderColor: 'rgba(75,192,192,1)',
        borderWidth: 2,
        data: [65, 59, 80, 81, 56, 55, 40],
      },
    ],
  };

  return (
    <div className="ml-64 w-full bg-[#f4f4f4] p-8">
      <h1 className="text-3xl font-bold mb-6">Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
        {/* Card 1: Total Sales */}
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-xl font-semibold">Total Sales</h2>
          <p className="text-2xl font-bold">$12,345</p>
        </div>

        {/* Card 2: Total Users */}
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-xl font-semibold">Total Users</h2>
          <p className="text-2xl font-bold">1,234</p>
        </div>

        {/* Card 3: Total Orders */}
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-xl font-semibold">Total Orders</h2>
          <p className="text-2xl font-bold">567</p>
        </div>
      </div>

      {/* Chart Section */}
      <div className="bg-white p-6 rounded-lg shadow-md mb-6">
        <h2 className="text-xl font-semibold mb-4">Sales Overview</h2>
      </div>

      {/* Recent Activity Section */}
      <div className="bg-white p-6 rounded-lg shadow-md">
        <h2 className="text-xl font-semibold mb-4">Recent Activity</h2>
        <ul>
          <li className="border-b py-2">User  John Doe made a purchase of $100.</li>
          <li className="border-b py-2">User  Jane Smith registered.</li>
          <li className="border-b py-2">User  Alex Johnson updated their profile.</li>
          <li className="border-b py-2">User  Emily Davis made a purchase of $50.</li>
        </ul>
      </div>
    </div>
  );
};

export default Container;