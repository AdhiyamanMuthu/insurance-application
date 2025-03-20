import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';
import FilterModal from './components/FilterModal';

function App() {
  const [policies, setPolicies] = useState([]);
  const[name, setName] = useState('');
  const [filters, showFilters] = useState(false);

  const [order, setOrder] = useState('ASC');
  const [field, setField] = useState('name');
  
  const[error, setError] = useState();


  function  toggleOrder(){
    if(order === 'ASC'){
      setOrder('DESC');
    } else {
      setOrder('ASC');
    }
  }
  useEffect(() => {
    const url = `http://localhost:8080/api/v1/policies?name=${name}&sorted=${order}&field=${field}`;
    axios.get(url).then(res => {
      setPolicies(res.data);
    }).catch(err => {
      setError(err);
    });
  }, [name, order])
  return (
    <div className="App">
      <FilterModal isOpen={filters} setIsOpen={showFilters} setPolicies={setPolicies} ></FilterModal>
      <header className="App-header">
        <div > <h1>Insurance bazaar</h1>  
        <button>Login</button>
        <button>Sign up</button>
        </div>
        <div>
          <h2>Policies</h2>
          <div>  
            <input id='search' placeholder='Search policies by name' onChange={(e) => {setName(e.target.value)}}></input> 
              
              <button onClick={(e) => {
                  showFilters(!filters);
              }}> Filters</button> 
          </div>
          
          <div>
            <div className='container'>
              <div>
                <table>
                  <thead>
                    <tr> 
                      <td>Policy ID</td>
                      <td>Policy Name
                      <button onClick={(e) =>{
                        toggleOrder();
                        setField('name');
                      }}>
                        ⌄
                      </ button>
                      
                      </td>
                      <td>Type
                      <button onClick={(e) =>{
                        toggleOrder();
                        setField('type');
                      }}>
                        ⌄
                      </ button>
                      
                      </td>
                      <td >Coverage 
                        <button onClick={(e) =>{
                        toggleOrder();
                        setField('coverage');
                      }}>
                        ⌄
                      </ button>
                      </td>

                      <td >Premium 
                      <button onClick={(e) =>{
                        toggleOrder();
                        setField('premium');
                      }}>
                        ⌄
                      </ button>
                      </td>
                    </tr>
                  </thead>
                  <tbody>
                      {policies.map((policy, index) => {
                         return <tr key={index}>
                              <td> {policy.id} </td>
                              <td> {policy.name} </td>
                              <td> {policy.type} </td>
                              <td> {policy.coverage} </td>
                              <td> {policy.premium} </td>
                              <td> <button>Buy</button> </td>
                              </tr>
                          })}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </header>
    </div>
  );
}

export default App;
