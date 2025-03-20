import axios from "axios";
import { useState } from "react";
import Modal from "react-modal";

const FilterModal = ({ isOpen, setIsOpen, setPolicies }) => {
  const [coverageFrom, setCoverageFrom] = useState('');
  const [coverageTo, setCoverageTo] = useState('');
  const [premiumFrom, setPremiumFrom] = useState('');
  const [premiumTo, setPremiumTo] = useState('');
  const [type, setType] = useState('');
  
  const [msg, setMsg] = useState('');

  function applyFilters(){
    if(coverageFrom === '' || coverageTo === '' || premiumFrom === '' || premiumTo === '' || type === ''){
      setMsg('All fields are mandatory to apply filters');
      return;
    }

    axios.get(`http://localhost:8080/api/v1/search?coverBetween=${coverageFrom}-${coverageTo}&premBetween=${premiumFrom}-${premiumTo}&type=${type}`).then(res => {
      console.log(res.data);
      setPolicies(res.data);
      setMsg('Filters applied successfully');
    }).catch(err => {
      console.log(err);
      setMsg(err.response.data.message);
    });
  }

    return (
      <Modal
        isOpen={isOpen}
        contentLabel="Search Modal"
      >
        <h2>Apply Filters to Policies</h2>
        <p>All fields are mandatory to apply filters</p>
        <div>
        <label> Policy Type :</label>
         <input placeholder="Policy type" onChange={(e) => {setType(e.target.value)}}></input>
          <div>
          <label> Coverage :</label>
          <input required placeholder="from" onChange={(e) => {setCoverageFrom(e.target.value)}}></input> to 
          <input required placeholder="to" onChange={(e) => {setCoverageTo(e.target.value)}}></input>
          </div>
          <label> Premium : </label>
         <input required placeholder="from" onChange={(e) => {setPremiumFrom(e.target.value)}}></input> to 
         <input required placeholder="to" onChange={(e) => {setPremiumTo(e.target.value)}}></input>
        </div>
        <button
          onClick={(e) => {
            applyFilters();
            setIsOpen(!isOpen)
          }}

        >
          Apply filters
        </button>
      </Modal>
    );
  };

export default FilterModal;