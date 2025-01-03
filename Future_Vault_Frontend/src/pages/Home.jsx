import React from 'react'
import { Link } from 'react-router-dom';
import { Lock, MessageSquareLock, Timer } from 'lucide-react';
import Button from '../components/Button';

function Home() {
  return (
<>
<div className='bg-zinc-900 h-screen'>
<nav className="bg-zinc-900">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <Link to="/" className="flex items-center">
              <Timer className="h-10 w-10 text-purple-600" />
              <span className="ml-1 text-2xl font-extrabold text-white">Futurevault</span>
            </Link>
          </div>
          <div className="flex items-center space-x-4">
                <Link to="/register">
                <Button button={'sign-up'} />
                </Link>
                <Link to="/login">
                <Button button={'sign-in'} />
                </Link>
          </div>
        </div>
      </div>
    </nav>
    <div className="min-h-screen bg-zinc-900">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="py-12 sm:py-24">
          <div className="text-center">
            <h1 className="text-4xl font-extrabold text-purple-900 sm:text-5xl md:text-6xl">
              Preserve Your Memories
              <span className="block text-purple-400">Share Them in the Future</span>
            </h1>
            <p className="mt-3 max-w-md mx-auto text-base text-white sm:text-lg md:mt-5 md:text-xl md:max-w-3xl">
              Create digital time capsules to store your precious memories and messages.
              Share them with friends and loved ones when the time is right.
            </p>
            <div className="mt-5 max-w-md mx-auto sm:flex sm:justify-center md:mt-8">
              <Link to="/register">
              <Button button={'Get Started'} />
              </Link>
            </div>
          </div>

          <div className="mt-24">
            <div className="grid grid-cols-1 gap-8 md:grid-cols-3">
              <div className="flex flex-col items-center p-6 bg-zinc-800 rounded-lg shadow transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover: duration-300 ...">
                <Timer className="h-12 w-12 text-purple-600" />
                <h3 className="mt-4 text-xl font-medium text-purple-300">Time-Based Sharing</h3>
                <p className="mt-2 text-center text-white">
                  Set a future date for your capsule to be opened and shared.
                </p>
              </div>
              <div className="flex flex-col items-center p-6 bg-zinc-800 rounded-lg shadow transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover: duration-300 ...">
                <Lock className="h-12 w-12 text-purple-600" />
                <h3 className="mt-4 text-xl font-medium text-purple-300">Private & Secure</h3>
                <p className="mt-2 text-center text-white">
                  Your memories are encrypted and secure until the reveal date.
                </p>
              </div>
              <div className="flex flex-col items-center p-6 bg-zinc-800 rounded-lg shadow transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover: duration-300 ...">
                <MessageSquareLock className="h-12 w-12 text-purple-600" />
                <h3 className="mt-4 text-xl font-medium text-purple-300">Secure Messaging Made Simple</h3>
                <p className="mt-2 text-center text-white">
                Deliver your messages with confidence, secure mail and SMS, crafted for trust and reliability.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</>
  )
}

export default Home;
