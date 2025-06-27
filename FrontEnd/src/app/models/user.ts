import { UserRole } from "../enums/user-role.enum";

export interface User {
  name: string;
  email: string;
  password?: string;
  role: UserRole;
}
