import { Timer} from 'lucide-react'
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import Input from '../components/Input'
import Button from '../components/Button'
import axios from 'axios'

function Register() {

  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const[message, setMessage] = useState('');
  const [isSuccess, setIsSuccess] = useState(false);
  const navigate = useNavigate();

  const handleRegister = async (e)=>{
    e.preventDefault();
       
    try {
      const response = await axios.post("http://localhost:1008/auth/register",{username, email, password});

      if (response.status === 200) {
        setMessage(email+'  account created..!');
        setIsSuccess(true);

        setTimeout(()=>{
          navigate('/login')
        },2000);

      }
    } catch (error) {
       
      if (error.response) {
         
          if (error.response.status === 409 ) {
            setMessage(email + ' user already exist..!')
          } else if(error.response.status === 500) {
            setMessage('please check your details')
          }
      } else {
        setMessage('please try again..!')
      }
       setIsSuccess(false);    
    }
  };

  return (
     <>
     <div className="bg-zinc-900 h-screen">
     <nav>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <Link to="/" className="flex items-center">
              <Timer className="h-10 w-10 text-purple-600" />
              <span className="ml-1 text-2xl font-extrabold text-white">Futurevault</span>
            </Link>
          </div>
        </div>
      </div>
    </nav>
    {message &&(
        <div className= {`max-w-sm mx-auto p-1 text-center rounded-md ${isSuccess ? 'bg-green-600':'bg-red-600'}`} >
            <p className=' text-white' >{message}</p>
        </div>  
    )}
    <div className='max-w-md mx-auto mt-10 p-7  bg-zinc-800 shadow transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover: duration-300 ... ' >
    <h1 className="text-2xl font-bold mb-6 text-purple-600 ">Create Account</h1>
    <form  className="space-y-4 p-8" onSubmit={handleRegister} >
      <div>
        <label htmlFor="name" className="block text-sm font-medium text-white">
          Username
        </label>
        <Input type="text" value={username} onChange={(e)=>setUsername(e.target.value)}  required/>
      </div>
      <div>
        <label htmlFor="email" className="block text-sm font-medium text-white">
          Email
        </label>
        <Input  type="email" value={email} onChange={(e)=>setEmail(e.target.value)}  required/>
      </div>
      <div>
        <label htmlFor="password" className="block text-sm font-medium text-white">
          Password
        </label>
        <Input  type="password" value={password} onChange={(e)=>setPassword(e.target.value)}  required/>
      </div>
<div className='flex gap-16' >
<Button button={'Create'}  type='submit'/>
        <Link to="/login">
         <p className='text-white mt-2 hover:text-purple-600 hover:underline' >Already have account</p>
        </Link>
</div>
    </form>
    </div>
     </div>
     </>
  )
}

export default Register;
