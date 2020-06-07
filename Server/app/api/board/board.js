const database = require('../../../data')
const moment = require('moment')

require('moment-timezone')
moment.tz.setDefault('Asia/Seoul')
    
exports.write = (req, res) => {
    console.log('write notice')
    var body = req.body
    
    var type = body.type
    var title = body.title
    var contents = body.contents
    var good = '0'
    var time = moment().format('YYYY-MM-DD HH:mm:ss')

    console.log(req.session.id)
    console.log(req.session.user_id)

    database.Board.findAll({
        where :{
            type : type
        }
    }).then( (results) => {
        //console.log(results.length)
        if(!results.length) {
            var board_id = 1
            database.Board.create({
                board_id: board_id,
                type: type,
                title: title,
                contents: contents,
                user_id: req.session.user_id,
                good: good,
                time: time
        
            }).then( () => {
                console.log(req.session.user_id, 'successed ')
                res.status(200).json({ result : "Success" })
            }).catch( (err) => {
                console.log(err)
                console.log(req.session.user_id, 'failed')
                res.status(404).json({ result: "Undefined error" })
            })  
        }
        else {
            var board_id = results.length+1
            database.Board.create({
                board_id: board_id,
                type: type,
                title: title,
                contents: contents,
                user_id: req.session.user_id,
                good: good,
                time: time
        
            }).then( () => {
                console.log(req.session.user_id, 'successed ')
                res.status(200).json({ result : "Success" })
            }).catch( (err) => {
                console.log(err)
                console.log(req.session.user_id, 'failed')
                res.status(404).json({ result: "Undefined error" })
            })  
        }
    }).catch( (err) => {
        console.log(err)
        res.status(404).json({ result: "Undefiend error" })
    })
   
    
}