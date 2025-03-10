import { useNavigate } from 'react-router-dom';

type PageType = 'main' | 'login' | 'signup' | 'registeremail' | '' | 'error';

const useMovePage = () => {
  const navigate = useNavigate();

  const move = (page: PageType) => {
    navigate(`/${page}`);
  };
  return move;
};

export default useMovePage;
