const database = require('../../../data')
const moment = require('moment')

require('moment-timezone')
moment.tz.setDefault('Asia/Seoul')
    
exports.send = (req, res) => {
    console.log('send chat')
    var body = req.body
    var student_id = body.student_id
    var grade = body.grade
    var contents = body.contents
    var time = moment().format('YYYY-MM-DD HH:mm:ss')

    database.Chat.create({
        student_id: student_id,
        grade: grade,
        contents: contents,
        usecontentsr_id: contents,
        time: time
    }).then( () => {
        console.log('successed to send')
        res.status(200).json({ result : "Success" })
    }).catch( () => {
        console.log('failed to send')
        res.status(404).json({ result: "Undefined error" })
    })
}

exports.load = (req, res) => {
    console.log('load char')
    var grade = req.body.grade
    database.Chat.findAll({
        where : {
            grade : grade
        }
    }).then( (result) =>{
        console.log('successed to load')
        res.status(200).json(result)
    }).catch( () => {
        console.log('failed to load')
        res.status(404).json({result : 'Undefined err'})
    })
}
exports.delete = (req, res) => {
    console.log('delete chat')
    var body = req.body
    var chat_id = body.id
    database.Chat.destroy({
        where : {
            id : chat_id
        }
    }).then( (result) =>{
        console.log('successed to delete')
        res.status(200).json(result)
    }).catch( () => {
        console.log('failed to delete')
        res.status(404).json({result : 'Undefined err'})
    })
}
