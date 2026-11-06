export interface Users {
    id: string | number;
    name: string;
    lastName: string;
    email: string;
    createdAt: string;
    status: string;
    lastLoginAt: string;
    userRole: string;
}

export type CreateUserPayload = Omit<Users, 'id' | 'createdAt'> & {
    password?: string;
};

