# NewsApp

## 1. TopNews Tag
<img src="https://user-images.githubusercontent.com/42669772/189471854-47e3af3b-55b3-4357-92f6-8f5222f2f863.PNG" width="300" height="600"/>  <img src="https://user-images.githubusercontent.com/42669772/189471855-018ae442-feb2-4b97-8eda-011a2e9a4c56.PNG" width="300" height="600"/>

리사이클러뷰로 TopNews 화면을 구성했고 클릭 시 해당 기사로 이동하게끔 구현되었습니다.

## 2. Category Tag
<img src="https://user-images.githubusercontent.com/42669772/189471949-3b01d3fe-4e8a-4c08-93d9-95c94e46c978.PNG" width="300" height="600"/><img src="https://user-images.githubusercontent.com/42669772/189471951-9da77409-3935-44d9-8ab4-385d86125538.PNG" width="300" height="600"/><img src="https://user-images.githubusercontent.com/42669772/189471954-2df2b83e-a138-4c00-8d02-fe507764aa55.PNG" width="300" height="600"/> 

카테고리를 클릭하면 해당 카테고리에 맞는 기사를 얻어오고 마찬가지로 클릭 시 해당 기사로 이동합니다.

## 3. Saved Tag
<img src="https://user-images.githubusercontent.com/42669772/189472033-fb16bc8d-ce9d-46e1-9591-0d0601566b75.PNG" width="300" height="600"/><img src="https://user-images.githubusercontent.com/42669772/189472034-107aa46b-4dee-4ff9-a105-b8844171647d.PNG" width="300" height="600"/><img src="https://user-images.githubusercontent.com/42669772/189472035-f3628c75-a649-455e-9a51-e0b73dc6548d.PNG" width="300" height="600"/>   
<img src="https://user-images.githubusercontent.com/42669772/189472036-392e26af-533a-4594-a353-9d6da7eb3d25.PNG" width="300" height="600"/> <img src="https://user-images.githubusercontent.com/42669772/189472038-50815849-186d-4b26-8100-a5a03347fb08.PNG" width="300" height="600"/>

기사의 오른쪽 상단에 있는 별 표시를 클릭하면 노란색으로 변하고 SavedFragment에 표시됩니다.
다시 한번 누르면 다시 비어있는 별로 변하고 SaveFragment에서 삭제됩니다.
만약 저장한 기사가 하나도 없으면 비어있음을 알려줍니다. 

## 4. 기술 스택

- Retrofit
- Room
- Navigation 
- DataBinding 
- ViewModel
- Coroutine 
- Coil
- Gson
- Hilt
