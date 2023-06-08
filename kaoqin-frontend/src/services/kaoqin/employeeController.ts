// @ts-ignore
/* eslint-disable */
import request from '@/plugins/globalRequest';

/** addEmployee POST /api/employee/add */
export async function addEmployeeUsingPOST(
  body: API.EmployeeAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponselong>('/api/employee/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteEmployee POST /api/employee/delete */
export async function deleteEmployeeUsingPOST(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/employee/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getEmployeeById GET /api/employee/get */
export async function getEmployeeByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getEmployeeByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseEmployee>('/api/employee/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listEmployee GET /api/employee/list */
export async function listEmployeeUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listEmployeeUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListEmployeeVO>('/api/employee/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listEmployeeByPage GET /api/employee/list/page */
export async function listEmployeeByPageUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listEmployeeByPageUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageEmployee>('/api/employee/list/page', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateEmployee POST /api/employee/update */
export async function updateEmployeeUsingPOST(
  body: API.EmployeeUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/employee/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
