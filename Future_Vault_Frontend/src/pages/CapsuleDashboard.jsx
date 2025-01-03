import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { Timer, Lock, Unlock, X, CircleUserRound, MailCheck, PhoneForwarded, CalendarArrowUp, MessageCircle, Trash } from 'lucide-react';
import Button from '../components/Button';
import axios from 'axios';

function CapsuleDashboard() {
 
  /*Login Response Data*/

  const location = useLocation();
  const user = location.state?.user;
  const[capsules, setCapsules] = useState(user?.capsuleUserResponseDto || []);

  /* local storage credentials */
  const [credentials, setCredentials] = useState();
  
  useEffect(() => {
    const data = localStorage.getItem("userData");
    if (data) {
      setCredentials(JSON.parse(data));
    }
  }, []);

  const authHeader = credentials ? `Basic ${btoa(`${credentials.emi}:${credentials.pas}`)}` : '';


/* Create Capsules*/

const [isCreateCapsulePopupOpen, setIsCreateCapsulePopupOpen] = useState(false); // New state for the Create Capsule form visibility
const toggleCreateCapsulePopup = () => {
  setIsCreateCapsulePopupOpen(!isCreateCapsulePopupOpen);
};

const [title, setTitle] = useState('');
const [message, setMessage] = useState('');
const [targetemail, setTargetemail] = useState('');
const [targetmobile, setTargetmobile] = useState(0);
const [unloackdatetime, setUnloackdatetime] = useState('');

const handleCapsuleSubmit = async (e) =>{

  e.preventDefault();

       try {
        const capsuleResponse = await axios.post(`http://localhost:1008/capsules/create/${user?.userid}`
          ,{title, message, targetemail, targetmobile, unloackdatetime},{
           headers: {
             Authorization: authHeader,
           },
          });
         
          if (capsuleResponse.status === 200) {
            setCapsules((prev) => [...prev, capsuleResponse.data])
            setTitle('');
            setMessage('');
            setTargetemail('');
            setTargetmobile(0);
            setUnloackdatetime('');
          }

       } catch (error) {
        console.error('Error adding capsule:', error);  
       }
};

/* User popup open & close */
const toggleUserPopup = () => {
  setIsUserPopupOpen(!isUserPopupOpen);
};



 /* Capsule Retrieving */
  const [isUserPopupOpen, setIsUserPopupOpen] = useState(false);
  const [selectedCapsule, setSelectedCapsule] = useState();
  const [note, setNote] = useState("");


  const openCapsulePopup = async (capsuleid, unlockdateTime) => {
    try {
      const response = await axios.get(`http://localhost:1008/capsules/get/${capsuleid}`, {
        headers: {
          Authorization: authHeader,
        },
      });
      if (response.status === 200) {
        const capsuleData = response.data;
        setSelectedCapsule(capsuleData);
      }
    } catch (error) {
      console.log(error);
      if (error.response?.status === 403) {
        setNote(`Capsule is locked. Opens at: ${unlockdateTime}`);
      } else {
        setNote("Error occurred. Please try again.");
      }
    }
  };

  const closeCapsulePopup = () => {
    setSelectedCapsule(null);
    setNote("");
  };

/* user logout */

const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("userData");
    navigate('/login');
  };


  /*Capsule Deletion*/

const deleteCapsule = async (capsuleid) =>{

         try {
         
          const response = await axios.delete(`http://localhost:1008/capsules/delete/${capsuleid}`,{
                      headers: {
                        Authorization: authHeader,
                      },
          });

          if (response.status === 200) {
            setCapsules((prev) => prev.filter((capsules) => capsules.capsuleid !== capsuleid))
          }

         } catch (error) {
             console.log(error)
         }

}
  
  return (
    <>
      <div className="bg-zinc-900 h-screen">
        <nav>
          <div className="max-w-7xl px-4 sm:px-6 lg:px-8">
            <div className="flex justify-between h-16">
              <div className="flex items-center">
                <Link to="/" className="flex items-center">
                  <Timer className="h-10 w-10 text-purple-600" />
                  <span className="ml-1 text-2xl font-extrabold text-white">Futurevault</span>
                </Link>
              </div>
              <div className='flex gap-28'>
              <div
                className="text-white text-xl mt-3 cursor-pointer flex"
                onClick={toggleUserPopup}
              >
                <CircleUserRound className="text-purple-600 mr-3 h-8 w-8" />
                <p className="font-bold text-2xl">Hi {user?.username || "User"}</p>
              </div>
              <div className="mt-3">
                <Button button="Sign Out" onClick={handleLogout} />
              </div>
              </div>
            </div>
          </div>
        </nav>
        <div className="mt-3 ml-6">
          <Button button="Create Capsule" onClick={toggleCreateCapsulePopup} /> {/* Open the form popup */}
        </div>
        <div className="space-y-6">
          {capsules.length > 0 ? (
            <div className="m-5 grid gap-4 md:grid-cols-4">
              {capsules.map((capsule) => (
                <div
                  key={capsule.capsuleid}
                  onClick={() => openCapsulePopup(capsule.capsuleid, capsule.unlockdateTime)}
                  className="cursor-pointer block p-6 bg-zinc-800 rounded-lg transition-all ease-in-out transform hover:scale-105 hover:shadow-lg"
                >
                  <Trash className='text-purple-600 mb-5 h-4 w-4 hover:text-red-600' onClick={() => deleteCapsule(capsule.capsuleid)} />
                  <div className="flex justify-between items-start">                    
                    <h3 className="text-lg font-bold text-white">{capsule.capsuletitle}</h3>
                    {new Date() > new Date(capsule.unlockdateTime) ? (
                      <Unlock className="h-5 w-5 text-purple-600" />
                    ) : (
                      <Lock className="h-5 w-5 text-purple-600" />
                    )}
                  </div>
                  <div className="mt-4 text-sm text-purple-400">
                    Opens on: {new Date(capsule.unlockdateTime).toLocaleDateString()}
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <p className="text-white text-3xl font-bold text-center">No Time Capsules Available</p>
          )}
        </div>
      </div>

      {/* User Details Popup */}
      {isUserPopupOpen && (
        <div className="fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-gradient-to-r from-purple-600 via-indigo-600 to-blue-600 p-8 rounded-lg shadow-lg w-1/3 max-w-md transform transition-all scale-110">
            <div className="flex justify-between items-center mb-6">
              <CircleUserRound className="text-white mr-3 h-10 w-10" />
              <X className="h-6 w-6 cursor-pointer text-white hover:text-red-500" onClick={toggleUserPopup} />
            </div>
            <p className="text-white text-xl font-semibold mb-2">ID: {user?.userid}</p>
            <p className="text-white text-xl font-semibold mb-2">Name: {user?.username}</p>
            <p className="text-white text-xl font-semibold mb-2">Email: {user?.useremail}</p>
          </div>
        </div>
      )}

      {/* Capsule Details Popup */}
      {selectedCapsule ? (
        <div className="fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-gradient-to-r from-purple-600 via-indigo-600 to-blue-600 p-8 rounded-lg shadow-lg w-1/3 max-w-md transform transition-all scale-110">
            <div className="flex justify-between items-center mb-6">
              <div className='flex gap-4 items-center'>
                <Unlock className="h-6 w-6 text-white" />
                <p className="text-white text-lg font-bold">{selectedCapsule.title}</p>
              </div>
              <X className="h-6 w-6 cursor-pointer text-white hover:text-red-500" onClick={closeCapsulePopup} />
            </div>
            <div className="space-y-4">
              <div className="flex items-center gap-4">
                <MessageCircle className="text-white h-6 w-6" />
                <p className="text-white text-lg"><span className="font-semibold">Message Sent:</span> {selectedCapsule.message}</p>
              </div>
              <div className="flex items-center gap-4">
                <MailCheck className="text-white h-6 w-6" />
                <p className="text-white text-lg"><span className="font-semibold">Email Delivered:</span> {selectedCapsule.targetemail}</p>
              </div>
              <div className="flex items-center gap-4">
                <PhoneForwarded className="text-white h-6 w-6" />
                <p className="text-white text-lg"><span className="font-semibold">SMS Delivered:</span> {selectedCapsule.targetmobile}</p>
              </div>
              <div className="flex items-center gap-4">
                <CalendarArrowUp className="text-white h-6 w-6" />
                <p className="text-white text-lg"><span className="font-semibold">Opened:</span> {new Date(selectedCapsule.openDateTime).toLocaleString()}</p>
              </div>
            </div>
          </div>
        </div>
      ) : (
        note && (
          <div className="fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center z-50">
            <div className="bg-gradient-to-r from-purple-600 via-indigo-600 to-blue-600 p-8 rounded-lg shadow-lg w-1/3 max-w-md transform transition-all scale-110">
              <div className="flex justify-between items-center mb-6">
                <X className="h-6 w-6 cursor-pointer text-white hover:text-red-500" onClick={closeCapsulePopup} />
              </div>
              <div className="flex items-center gap-4">
                <Lock className="text-white h-6 w-6" />
                <h3 className="text-lg font-semibold text-white">{note}</h3>
              </div>
            </div>
          </div>
        )
      )}

      {/* Create Capsule Form Popup */}
      {isCreateCapsulePopupOpen && (
        <div className="fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-zinc-800 p-8 rounded-lg shadow-lg w-1/3 max-w-md">
            <div className="flex justify-between items-center mb-6">
              <h3 className="text-white text-2xl font-bold">Create Capsule</h3>
              <X className="h-6 w-6 cursor-pointer text-white hover:text-red-500" onClick={toggleCreateCapsulePopup} />
            </div>
          
            {/* Form for creating a capsule */}
            <form onSubmit={handleCapsuleSubmit}>
              <div className="mb-4">
                <label htmlFor="title" className="text-white">Title</label>
                <input
                  id="title"
                  type="text"
                  className="w-full p-2 mt-2 bg-zinc-700 text-white border border-zinc-600 rounded"
                  value={title}
                  onChange={(e)=>setTitle(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label htmlFor="message" className="text-white">Message</label>
                <textarea
                  id="message"
                  className="w-full p-2 mt-2 bg-zinc-700 text-white border border-zinc-600 rounded"
                  value={message}
                  onChange={(e)=>setMessage(e.target.value)}
                ></textarea>
              </div>
              <div className="mb-4">
                <label htmlFor="email" className="text-white">Receiver Email</label>
                <input
                  id="email"
                  type="email"
                  className="w-full p-2 mt-2 bg-zinc-700 text-white border border-zinc-600 rounded"
                  value={targetemail}
                  onChange={(e)=>setTargetemail(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label htmlFor="mobile" className="text-white">Receiver Mobile Number</label>
                <input
                  id="title"
                  type="number"
                  className="w-full p-2 mt-2 bg-zinc-700 text-white border border-zinc-600 rounded"
                  value={targetmobile}
                  onChange={(e)=>setTargetmobile(e.target.value)}
                />
              </div>
              <div className="mb-4">
                <label htmlFor="date" className="text-white">Unlock Date & Time</label>
                <input
                  id="date"
                  type="text"
                  className="w-full p-2 mt-2 bg-zinc-700 text-white border border-zinc-600 rounded"
                  value={unloackdatetime}
                  onChange={(e)=> setUnloackdatetime(e.target.value)}
                />
              </div>
              <Button  button="Create Capsule" type="submit"/>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export default CapsuleDashboard;
