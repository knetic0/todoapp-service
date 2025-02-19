import { PrimeReactProvider } from 'primereact/api';
import Router from './router';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ToastContextProvider } from './context/toast-context';

const client = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
    },
    mutations: {
      retry: 1,
    }
  }
});

function App() {
  return (
    <QueryClientProvider client={client}>
      <PrimeReactProvider>
        <ToastContextProvider>
          <Router />
        </ToastContextProvider>
      </PrimeReactProvider>
    </QueryClientProvider>
  );
}

export default App;
