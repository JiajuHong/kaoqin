// @ts-ignore
/* eslint-disable */
import request from '@/plugins/globalRequest';

/** addDepartment POST /api/department/add */
export async function addDepartmentUsingPOST(
  body: API.DeptAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponselong>('/api/department/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteDepartment POST /api/department/delete */
export async function deleteDepartmentUsingPOST(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/department/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getDepartmentById GET /api/department/get */
export async function getDepartmentByIdUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getDepartmentByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseDepartment>('/api/department/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listDepartment GET /api/department/list */
export async function listDepartmentUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listDepartmentUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListDepartment>('/api/department/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listDepartmentByPage GET /api/department/list/page */
export async function listDepartmentByPageUsingGET(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.listDepartmentByPageUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageDepartment>('/api/department/list/page', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateDepartment POST /api/department/update */
export async function updateDepartmentUsingPOST(
  body: API.DeptUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseboolean>('/api/department/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
