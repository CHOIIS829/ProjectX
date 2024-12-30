import MemberContext from './context/memberContext';
import { Router } from './router/Router';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

function App() {
    const queryClient = new QueryClient();
	
    return (
        <QueryClientProvider client={queryClient}>
            <MemberContext.Provider>
                <Router />
            </MemberContext.Provider>
        </QueryClientProvider>
    );
}

export default App;
