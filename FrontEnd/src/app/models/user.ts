import { UserRole } from "../enums/user-role.enum";

export interface User {
  id: number;
  fullName: string;
  email: string;
  password?: string;
  role: UserRole;
  leaveBalance?: number;
}
